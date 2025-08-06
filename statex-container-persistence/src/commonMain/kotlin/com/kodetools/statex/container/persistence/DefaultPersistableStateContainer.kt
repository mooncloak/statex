package com.kodetools.statex.container.persistence

import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.kodetools.statex.container.StateContainer
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule

@Stable
@ExperimentalPersistentStateAPI
public class DefaultPersistableStateContainer<T> @PublishedApi internal constructor(
    defaultValue: T,
    private val storage: PersistentStorage,
    private val key: String,
    private val serializer: KSerializer<T>,
    private val serializersModule: SerializersModule,
    public val policy: SnapshotMutationPolicy<T>,
    private val dispatcher: MainCoroutineDispatcher
) : PersistableStateContainer<T> {

    override val initial: StateFlow<T>
        get() = mutableInitial

    override val current: StateFlow<T>
        get() = mutableCurrent

    private val mutableInitial: MutableStateFlow<T>
    private val mutableCurrent: MutableStateFlow<T>
    private val mutableChanged = mutableStateOf(false)
    private val mutableFlow: MutableStateFlow<T>

    private val mutex = Mutex(locked = false)

    init {
        val initialValue = storage.get(
            key = key,
            defaultValue = defaultValue,
            serializer = serializer,
            serializersModule = serializersModule
        )

        mutableInitial = MutableStateFlow(initialValue)
        mutableCurrent = MutableStateFlow(initialValue)
        mutableFlow = MutableStateFlow(value = initialValue)
    }

    override suspend fun snapshot(): StateContainer.SnapshotStateModel<T> =
        mutex.withLock {
            StateContainer.SnapshotStateModel(
                initialStateValue = initial.value,
                currentStateValue = current.value,
            )
        }

    override suspend fun update(block: suspend (current: T) -> T) {
        mutex.withLock {
            val value = block.invoke(current.value)

            if (value != current.value) {
                storage.set(
                    key = key,
                    value = value,
                    serializer = serializer,
                    serializersModule = serializersModule
                )

                // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
                withContext(dispatcher) {
                    mutableCurrent.value = value
                    mutableChanged.value = true
                    mutableFlow.value = value
                }
            }
        }
    }

    override suspend fun reset(block: suspend (T) -> T) {
        mutex.withLock {
            val initialStateValue = block.invoke(initial.value)

            storage.set(
                key = key,
                value = initialStateValue,
                serializer = serializer
            )

            // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
            withContext(dispatcher) {
                mutableInitial.value = initialStateValue
                mutableCurrent.value = initialStateValue
                mutableChanged.value = false
                mutableFlow.value = initialStateValue
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DefaultPersistableStateContainer<*>) return false

        if (mutableInitial != other.mutableInitial) return false
        if (mutableCurrent != other.mutableCurrent) return false
        if (mutableChanged != other.mutableChanged) return false
        if (mutableFlow != other.mutableFlow) return false
        if (policy != other.policy) return false

        return mutex == other.mutex
    }

    override fun hashCode(): Int {
        var result = mutableInitial.hashCode()
        result = 31 * result + mutableCurrent.hashCode()
        result = 31 * result + mutableChanged.hashCode()
        result = 31 * result + mutableFlow.hashCode()
        result = 31 * result + mutex.hashCode()
        result = 31 * result + policy.hashCode()
        return result
    }

    override fun toString(): String =
        "DefaultPersistableStateContainer(initial=$initial, current=$current, policy=$policy)"
}
