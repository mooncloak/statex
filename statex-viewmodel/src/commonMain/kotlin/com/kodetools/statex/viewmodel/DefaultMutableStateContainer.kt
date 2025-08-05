package com.kodetools.statex.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
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
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000),
    onInit: () -> Flow<T> = { emptyFlow() }
) : MutableStateContainer<T> {

    private val initialMutableStateFlow = MutableStateFlow(initialStateValue)
    private val currentMutableStateFlow = MutableStateFlow(currentStateValue)
    private val currentStateFlow = merge(
        onInit(),
        currentMutableStateFlow
    ).flowOn(flowDispatcher)
        .stateIn(
            scope = flowCoroutineScope,
            started = sharingStarted,
            initialValue = initialStateValue
        )

    override val initial: StateFlowContainer<T> = StateFlowContainer(initialMutableStateFlow)

    override val current: StateFlowContainer<T> = StateFlowContainer(currentStateFlow)

    private val mutex = Mutex(locked = false)

    override suspend fun snapshot(): StateContainer.SnapshotStateModel<T> =
        mutex.withLock {
            val initialStateValue = initial.state.value
            val currentStateValue = current.state.value

            StateContainer.SnapshotStateModel(
                initialStateValue = initialStateValue,
                currentStateValue = currentStateValue,
                isChanged = initialStateValue == currentStateValue
            )
        }

    override suspend fun update(block: suspend (current: T) -> T) {
        mutex.withLock {
            val value = block.invoke(current.state.value)

            if (value != current.state.value) {
                // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
                withContext(emitDispatcher) {
                    currentMutableStateFlow.value = value
                }
            }
        }
    }

    override suspend fun reset(block: suspend (initial: T) -> T) {
        mutex.withLock {
            val value = block.invoke(initial.state.value)

            // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
            withContext(emitDispatcher) {
                initialMutableStateFlow.value = value
                currentMutableStateFlow.value = value
            }
        }
    }
}
