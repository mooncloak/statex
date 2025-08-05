package com.kodetools.statex.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    public val initial: StateFlowContainer<T>

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
     * @see [MutableStateContainer.update] For a way of changing this value.
     * @see [MutableStateContainer.reset] For a way of resetting this value.
     */
    public val current: StateFlowContainer<T>

    /**
     * Creates a [SnapshotStateModel] of the all the current values within this [StateContainer].
     */
    public suspend fun snapshot(): SnapshotStateModel<T>

    /**
     * Represents the data associated with the current state of a [StateContainer] at a particular
     * instance.
     *
     * @property [initialStateValue] Corresponds to the [StateContainer.initial] value at the moment this
     * snapshot was taken.
     *
     * @property [currentStateValue] Corresponds to the [StateContainer.current] value at the moment this
     * snapshot was taken.
     *
     * @property [isChanged] Corresponds to the [StateContainer.changed] value at the moment this
     * snapshot was taken.
     */
    @Serializable
    public data class SnapshotStateModel<T> public constructor(
        @SerialName(value = "initial") public val initialStateValue: T,
        @SerialName(value = "current") public val currentStateValue: T,
        @SerialName(value = "changed") public val isChanged: Boolean = initialStateValue == currentStateValue
    )

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
     * stateContainer.update { current ->
     *     // Perform some logic
     *
     *     // Return an updated value from this function
     *     current.copy(...)
     * }
     * ```
     *
     * @param [block] The function that will be invoked to obtain the new [current] value.
     */
    public suspend fun update(block: suspend (currentStateValue: T) -> T)

    /**
     * Resets the state to the provided [initialValue]. This provides a way to override what the initial
     * value was by providing an [initialValue] as a parameter.
     *
     * > [!Note]
     * > This is different from invoking the [update] function as it sets all the values back to their initial state,
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
    public suspend fun reset(block: suspend (initialStateValue: T) -> T = { it })

    public companion object
}

/**
 * Updates the [StateContainer.current] value to be the provided [stateValue]. This is a convenience function that delegates
 * to the [MutableStateContainer.update] by providing a higher-order function that simply returns the provided [stateValue].
 *
 * > [!Note]
 * > All write operations for a [MutableStateContainer] are safe to access concurrently. This means that if another
 * > mutation is currently running while this function is invoked, then this function will suspend until that
 * > function has finished.
 *
 * ## Example Usage
 *
 * ```kotlin
 * stateContainer.update(value = newValue)
 * ```
 *
 * @param [stateValue] The value to change the [StateContainer.current] value to.
 */
public suspend fun <T> MutableStateContainer<T>.update(stateValue: T): Unit =
    this.update { stateValue }

/**
 * Resets the state to the provided [initialStateValue]. This provides a way to override what the initial
 * value was by providing an [initialStateValue] as a parameter.
 *
 * > [!Note]
 * > This is different from invoking the [update] function as it sets all the values back to their initial state,
 * > as if the initial value was the provided [initialStateValue].
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
 * @param [initialStateValue] The value to set as the initial value. Defaults to [StateContainer.initial].
 */
public suspend fun <T> MutableStateContainer<T>.reset(initialStateValue: T) {
    this.reset { initialStateValue }
}

/**
 * Creates a [MutableStateContainer] with the provided [initialValue].
 *
 * @param [initialValue] The default [StateContainer.initial] value and the starting
 * [StateContainer.current] value. Note that this value can change over time with a
 * [MutableStateContainer], if the [MutableStateContainer.reset] function is invoked.
 *
 * @param [policy] The [SnapshotMutationPolicy] that is used to construct the underlying
 * [MutableState] instances.
 *
 * @param [dispatcher] The [MainCoroutineDispatcher] that is used to dispatch the changes to the state. Defaults to
 * [Dispatchers.Main]. This could be updated to [Dispatchers.Main] [MainCoroutineDispatcher.immediate] on supported
 * platforms.
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
public fun <T> mutableStateContainerOf(
    initialStateValue: T,
    flowCoroutineScope: CoroutineScope,
    emitDispatcher: CoroutineDispatcher = Dispatchers.Main,
    flowDispatcher: CoroutineDispatcher = emitDispatcher,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000),
    onInit: () -> Flow<T> = { emptyFlow() }
): MutableStateContainer<T> = DefaultMutableStateContainer(
    initialStateValue = initialStateValue,
    flowCoroutineScope = flowCoroutineScope,
    emitDispatcher = emitDispatcher,
    flowDispatcher = flowDispatcher,
    sharingStarted = sharingStarted,
    onInit = onInit
)

/**
 * Creates a [MutableStateContainer] with the provided [snapshot].
 *
 * @param [snapshot] The [StateContainer.SnapshotStateModel] value containing the values to use
 * initially for the returned [MutableStateContainer] instance.
 *
 * @param [policy] The [SnapshotMutationPolicy] that is used to construct the underlying
 * [MutableState] instances.
 *
 * @param [dispatcher] The [MainCoroutineDispatcher] that is used to dispatch the changes to the state. Defaults to
 * [Dispatchers.Main]. This could be updated to [Dispatchers.Main] [MainCoroutineDispatcher.immediate] on supported
 * platforms.
 *
 * ## Example Usage
 *
 * ```kotlin
 * val initialStateContainer = mutableStateContainer(true)
 * val snapshot = initialStateContainer.snapshot()
 * val stateContainer = mutableStateContainerOf(snapshot)
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
public fun <T> mutableStateContainerOf(
    snapshot: StateContainer.SnapshotStateModel<T>,
    flowCoroutineScope: CoroutineScope,
    emitDispatcher: CoroutineDispatcher = Dispatchers.Main,
    flowDispatcher: CoroutineDispatcher = emitDispatcher,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000),
    onInit: () -> Flow<T> = { emptyFlow() }
): MutableStateContainer<T> = DefaultMutableStateContainer(
    initialStateValue = snapshot.initialStateValue,
    currentStateValue = snapshot.currentStateValue,
    flowCoroutineScope = flowCoroutineScope,
    emitDispatcher = emitDispatcher,
    flowDispatcher = flowDispatcher,
    sharingStarted = sharingStarted,
    onInit = onInit
)
