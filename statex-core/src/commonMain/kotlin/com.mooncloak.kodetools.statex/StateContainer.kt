package com.mooncloak.kodetools.statex

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.*

/**
 * A generic container around [State] values for a component. This encapsulates the [current] [State] value, while
 * retaining the [initial] [State] value, and providing means to listen to state changes outside of Jetpack Compose
 * components (Kotlinx Coroutines [Flow]).
 *
 * Instead of using a [State] directly, one could use the [StateContainer] and [MutableStateContainer] components to
 * encapsulate additional associated state, and handle mutations to the underlying value in a concurrency-safe manner.
 *
 * > [!Note]
 * > Implementations of this interface must guarantee conformance to the [Stable] annotation requirements.
 *
 * @see [MutableStateContainer] for a mutable version of this [StateContainer] interface.
 * @see [mutableStateContainerOf] To create a [MutableStateContainer] instance.
 */
@Stable
public interface StateContainer<T> {

    /**
     * The initial [State] when this component was first created or when it was last reset. This value does not change
     * when the [current] value changes. However, this value may change depending on the [StateContainer]
     * implementation.
     *
     * > [!Note]
     * > That this is a Compose [State] value and will trigger recompositions of `@Composable` functions when used in
     * > the context of a `@Composable` function.
     *
     * ## Example Usage
     *
     * ```kotlin
     * stateContainer.initial.value
     * ```
     *
     * @see [MutableStateContainer.reset] For a way of resetting this value.
     */
    public val initial: State<T>

    /**
     * The current [State] for this component. This value can change over time, so subsequent calls to access this
     * property can return different values.
     *
     * > [!Note]
     * > That this is a Compose [State] value and will trigger recompositions of `@Composable` functions when used in
     * > the context of a `@Composable` function.
     *
     * ## Example Usage
     *
     * ```kotlin
     * stateContainer.current.value
     * ```
     *
     * @see [MutableStateContainer.change] For a way of changing this value.
     * @see [MutableStateContainer.reset] For a way of resetting this value.
     */
    public val current: State<T>

    /**
     * A [Flow] of changes that occur to the [current] value over time. Whenever the [current] [State] value changes,
     * that new value is emitted through this [Flow]. This is a [StateFlow] which means that it can retain the last
     * emitted value which can be accessed like so: `container.stream.value`.
     *
     * > [!Note]
     * > This is a "hot" flow, so changes can be emitted to it even when there are no subscribers. Since this is a
     * > [StateFlow] instance, it can be shared by multiple subscribers.
     *
     * ## Example Usage
     *
     * ```kotlin
     * stateContainer.stream.onEach { ... }
     *                      .launchIn(coroutineScope)
     * ```
     */
    public val stream: StateFlow<T>

    /**
     * A [State] that determines whether the [current] value ever changed from the [initial] value. Once the [current]
     * value changes from the [initial] value, this should always return a [State] with a value of `true`, even if the
     * [current] value changed back to the same value of [initial], until it is reset.
     *
     * ## Example Usage
     *
     * ```kotlin
     * stateContainer.changed.value
     * ```
     *
     * @see [MutableStateContainer.reset] To see how to reset a [MutableStateContainer] back to its initial state.
     */
    public val changed: State<Boolean>

    public companion object
}

/**
 * A [StateContainer] that provides the ability to mutate the wrapped state values. All mutable operations should be
 * considered thread-safe and safe to access concurrently.
 *
 * > [!Note]
 * > Implementations of this interface must perform mutation operations in a concurrency-safe manner.
 *
 * @see [mutableStateContainerOf] To create an instance of this interface.
 */
@Stable
public interface MutableStateContainer<T> : StateContainer<T> {

    /**
     * Updates the [current] value to be the value obtained by invoking the provided [block] function. The [block]
     * function is invoked within a lock, so subsequent calls to read the contained state values, such as [current],
     * are guaranteed to return the same value, as no other mutations can occur until after the function returns and
     * its result is emitted.
     *
     * The provided [block] function is provided the [current] value for convenience. Though, explicitly accessing the
     * [current] value will return the same value.
     *
     * > [!Note]
     * > All write operations for a [MutableStateContainer] are safe to access concurrently. This means that if another
     * > mutation is currently running while this function is invoked, then this function will suspend until that
     * > function has finished.
     *
     * ## Example Usage
     *
     * ```kotlin
     * stateContainer.change { current ->
     *     // Perform some logic
     *
     *     // Return an updated value from this function
     *     current.copy(...)
     * }
     * ```
     *
     * @param [block] The function that will be invoked to obtain the new [current] value.
     */
    public suspend fun change(block: suspend (current: T) -> T)

    /**
     * Resets the state to the provided [initialValue]. This provides a way to override what the initial
     * value was by providing an [initialValue] as a parameter.
     *
     * > [!Note]
     * > This is different from invoking the [change] function as it sets all the values back to their initial state,
     * > as if the initial value was the provided [initialValue].
     *
     * > [!Note]
     * > All write operations for a [MutableStateContainer] are safe to access concurrently. This means that if another
     * > mutation is currently running while this function is invoked, then this function will suspend until that
     * > function has finished.
     *
     * ## Example Usage
     *
     * ```kotlin
     * // Reset back to the initial value when creating this StateContainer instance.
     * stateContainer.reset()
     *
     * // Reset to a new initial value.
     * stateContainer.reset(initialValue = newInitialValue)
     * ```
     *
     * @param [initialValue] The value to set as the initial value. Defaults to [StateContainer.initial].
     */
    public suspend fun reset(initialValue: T = this.initial.value)

    public companion object
}

/**
 * Updates the [StateContainer.current] value to be the provided [value]. This is a convenience function that delegates
 * to the [MutableStateContainer.change] by providing a higher-order function that simply returns the provided [value].
 *
 * > [!Note]
 * > All write operations for a [MutableStateContainer] are safe to access concurrently. This means that if another
 * > mutation is currently running while this function is invoked, then this function will suspend until that
 * > function has finished.
 *
 * ## Example Usage
 *
 * ```kotlin
 * stateContainer.change(value = newValue)
 * ```
 *
 * @param [value] The value to change the [StateContainer.current] value to.
 */
public suspend fun <T> MutableStateContainer<T>.change(value: T): Unit =
    change { value }

/**
 * Creates a [MutableStateContainer] with the provided [initialValue].
 *
 * ## Example Usage
 *
 * ```kotlin
 * val stateContainer = mutableStateContainerOf(true)
 *
 * stateContainer.current.value // true
 * stateContainer.initial.value // true
 * stateContainer.changed.value // false
 *
 * // Mutate the value
 * stateContainer.change(value = false)
 *
 * stateContainer.current.value // false
 * stateContainer.initial.value // true
 * stateContainer.changed.value // true
 * ```
 */
@Stable
public fun <T> mutableStateContainerOf(initialValue: T): MutableStateContainer<T> =
    DefaultMutableStateContainer(initialValue = initialValue)
