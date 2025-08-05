package com.kodetools.statex.viewmodel

import kotlinx.coroutines.flow.StateFlow

public interface StateFlowContainer<T> {

    public val state: StateFlow<T>

    public companion object
}

public operator fun <T> StateFlowContainer.Companion.invoke(
    state: StateFlow<T>
): StateFlowContainer<T> = DefaultStateFlowContainer(
    state = state
)

internal data class DefaultStateFlowContainer<T>(
    override val state: StateFlow<T>
) : StateFlowContainer<T>
