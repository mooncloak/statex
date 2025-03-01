package com.mooncloak.kodetools.statex

import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * A default [MutableStateContainer] implementation.
 */
@Stable
public class DefaultMutableStateContainer<T> internal constructor(
    initialValue: T,
    currentValue: T = initialValue,
    changed: Boolean = false,
    public val policy: SnapshotMutationPolicy<T>
) : MutableStateContainer<T> {

    override val initial: State<T>
        get() = mutableInitial

    override val current: State<T>
        get() = mutableCurrent

    override val flow: StateFlow<T>
        get() = mutableFlow.asStateFlow()

    override val changed: State<Boolean>
        get() = mutableChanged

    private val mutableInitial = mutableStateOf(initialValue, policy)
    private val mutableCurrent = mutableStateOf(currentValue, policy)
    private val mutableChanged = mutableStateOf(changed)
    private val mutableFlow = MutableStateFlow(value = initialValue)

    private val mutex = Mutex(locked = false)

    override suspend fun snapshot(): StateContainer.SnapshotStateModel<T> =
        mutex.withLock {
            StateContainer.SnapshotStateModel(
                initial = initial.value,
                current = current.value,
                changed = changed.value
            )
        }

    override suspend fun update(block: suspend (current: T) -> T) {
        mutex.withLock {
            val value = block.invoke(current.value)

            if (value != current.value) {
                // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
                withContext(Dispatchers.Main) {
                    mutableCurrent.value = value
                    mutableChanged.value = true
                }

                mutableFlow.value = value
            }
        }
    }

    override suspend fun reset(initialValue: T) {
        mutex.withLock {
            // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
            withContext(Dispatchers.Main) {
                mutableInitial.value = initialValue
                mutableCurrent.value = initialValue
                mutableChanged.value = false
            }

            mutableFlow.value = initialValue
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DefaultMutableStateContainer<*>) return false

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
        "DefaultMutableStateContainer(initial=$initial, current=$current, flow=$flow, changed=$changed, policy=$policy)"
}
