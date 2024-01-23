package com.mooncloak.kodetools.statex

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.*

/**
 * A generic container around [State] values for a component. This encapsulates the [current] [State] value, while
 * retaining the [initial] [State] value, and providing means to listen to state changes outside of Jetpack Compose
 * components (Kotlinx Coroutines [Flow]).
 */
@Stable
interface StateContainer<T> {

    /**
     * The initial [State] when this component was first created. This value does not change when the [current] value
     * changes. However, this value may change depending on the [StateContainer] implementation.
     */
    val initial: State<T>

    /**
     * The current [State] for this component. This value can change over time, so subsequent calls
     * to access this property can return different values.
     */
    val current: State<T>

    /**
     * A [Flow] of changes that occur to the [current] over time. Whenever the [current] [State] value changes, that
     * new value is emitted through this [Flow]. This is a [StateFlow] which means that it can retain the last emitted
     * value which can be accessed like so: `container.stream.value`.
     *
     * > [!Note]
     * > This is a "hot" flow, so changes can be emitted to it even when there are no subscribers. Since this is a
     * > [StateFlow] instance, it can be shared by multiple subscribers.
     */
    val stream: StateFlow<T>

    /**
     * A [State] that determines whether the [current] value ever changed from the [initial] value. Once the [current]
     * value changes from the [initial] value, this should always return a [State] with a value of `true`, even if the
     * [current] value changed back to the same value of [initial], until it is reset.
     *
     * @see [MutableStateContainer.reset] To see how to reset a [MutableStateContainer] back to its initial state.
     */
    val changed: State<Boolean>

    companion object
}

/**
 * A [StateContainer] that provides the ability to mutate the wrapped state values. All mutable operations should be
 * considered thread-safe.
 */
@Stable
interface MutableStateContainer<T> : StateContainer<T> {

    /**
     * Updates the [current] value to be the provided [value].
     *
     * @param [value] The value to change the [current] value to.
     */
    suspend fun change(value: T)

    /**
     * Resets the state to its [initialValue]. This provides a way to override what the initial
     * value was by providing an [initialValue] as a parameter.
     *
     * @param [initialValue] The value to set as the initial value. Defaults to
     * [StateContainer.initial].
     */
    suspend fun reset(initialValue: T = this.initial.value)

    companion object
}

/**
 * Creates a [MutableStateContainer] with the provided [initialValue].
 */
@Stable
fun <T> mutableStateContainerOf(initialValue: T): MutableStateContainer<T> =
    DefaultMutableStateContainer(initialValue = initialValue)
