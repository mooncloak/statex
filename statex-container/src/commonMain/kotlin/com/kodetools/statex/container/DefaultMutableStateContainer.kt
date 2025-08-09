package com.kodetools.statex.container

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * A default [MutableStateContainer] implementation.
 */
public class DefaultMutableStateContainer<T> internal constructor(
    initialStateValue: T,
    currentStateValue: T = initialStateValue,
    flowCoroutineScope: CoroutineScope,
    private val emitDispatcher: CoroutineDispatcher,
    flowDispatcher: CoroutineDispatcher,
    sharingStarted: SharingStarted = SharingStarted.Companion.WhileSubscribed(5_000),
    upstreamFlow: () -> Flow<T> = { emptyFlow() }
) : MutableStateContainer<T> {

    private val initialMutableStateFlow = MutableStateFlow(initialStateValue)
    private val currentMutableStateFlow = MutableStateFlow(currentStateValue)

    override val initial: StateFlow<T> = initialMutableStateFlow.asStateFlow()

    override val current: StateFlow<T> =
        merge(
            upstreamFlow(),
            currentMutableStateFlow
        ).flowOn(flowDispatcher)
            .stateIn(
                scope = flowCoroutineScope,
                started = sharingStarted,
                initialValue = initialStateValue
            )

    private val mutex = Mutex(locked = false)

    override suspend fun snapshot(): StateContainer.SnapshotStateModel<T> =
        mutex.withLock {
            val initialStateValue = initial.value
            val currentStateValue = current.value

            StateContainer.SnapshotStateModel(
                initialStateValue = initialStateValue,
                currentStateValue = currentStateValue
            )
        }

    override suspend fun update(block: suspend (current: T) -> T) {
        mutex.withLock {
            val value = block.invoke(current.value)

            if (value != current.value) {
                // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
                withContext(emitDispatcher) {
                    currentMutableStateFlow.value = value
                }
            }
        }
    }

    override suspend fun reset(block: suspend (initial: T) -> T) {
        mutex.withLock {
            val value = block.invoke(initial.value)

            // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
            withContext(emitDispatcher) {
                initialMutableStateFlow.value = value
                currentMutableStateFlow.value = value
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DefaultMutableStateContainer<*>) return false

        if (emitDispatcher != other.emitDispatcher) return false
        if (initial != other.initial) return false
        if (current != other.current) return false

        return mutex == other.mutex
    }

    override fun hashCode(): Int {
        var result = emitDispatcher.hashCode()
        result = 31 * result + initial.hashCode()
        result = 31 * result + current.hashCode()
        result = 31 * result + mutex.hashCode()
        return result
    }

    override fun toString(): String =
        "DefaultMutableStateContainer(emitDispatcher=$emitDispatcher, initial=$initial, current=$current, mutex=$mutex)"
}
