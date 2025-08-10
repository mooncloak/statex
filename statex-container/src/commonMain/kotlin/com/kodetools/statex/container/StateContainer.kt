package com.kodetools.statex.container

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.flow.*

/**
 * A generic container around [StateFlow] values. This encapsulates the [current] [StateFlow], while also retaining the
 * [initial] [StateFlow] value.
 *
 * Instead of using a [StateFlow] directly, one could use the [StateContainer] and [MutableStateContainer] components to
 * encapsulate additional associated state, and handle mutations to the underlying value in a concurrency-safe manner.
 *
 * @see [MutableStateContainer] for a mutable version of this [StateContainer] interface.
 * @see [mutableStateContainerOf] To create a [MutableStateContainer] instance.
 */
public interface StateContainer<T> {

    /**
     * The [StateFlow] of the initial value when this component was first created or when it was last reset. This value
     * does not change when the [current] value changes via [MutableStateContainer.update] function calls. However,
     * this value may change on invocations of the [MutableStateContainer.reset] function.
     *
     * ## Example Usage
     *
     * ```kotlin
     * stateContainer.initial.value
     * ```
     *
     * @see [MutableStateContainer.reset] For a way of resetting this value.
     */
    public val initial: StateFlow<T>

    /**
     * The [StateFlow] of the current value of this [StateContainer].
     *
     * ## Example Usage
     *
     * ```kotlin
     * stateContainer.current.value
     * ```
     *
     * @see [MutableStateContainer.update] For a way of changing this value.
     * @see [MutableStateContainer.reset] For a way of resetting this value back to the initial value.
     */
    public val current: StateFlow<T>

    /**
     * Creates a [SnapshotStateModel] of all the current values within this [StateContainer].
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
     */
    public data class SnapshotStateModel<T> public constructor(
        public val initialStateValue: T,
        public val currentStateValue: T,
    ) {

        public companion object
    }

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
     * Resets the state to the value obtained from invoking the provided [block] function.
     *
     * > [!Note]
     * > This is different from invoking the [update] function as it sets all the values back to their initial state,
     * > as if the initial value was the value obtained from invoking the provided [block] function.
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
     * stateContainer.reset { newInitialValue }
     * ```
     *
     * @param [block] The function that is invoked with the current initial state value and returns the initial state
     * value to reset this [MutableStateContainer] to. Defaults to a block that returns the current initial state value.
     */
    public suspend fun reset(block: suspend (initialStateValue: T) -> T = { it })

    public companion object
}

/**
 * Updates the [StateContainer.current] value to be the provided [stateValue]. This is a convenience function that
 * delegates to the [MutableStateContainer.update] by providing a higher-order function that simply returns the
 * provided [stateValue].
 *
 * > [!Note]
 * > All write operations for a [MutableStateContainer] are safe to access concurrently. This means that if another
 * > mutation is currently running while this function is invoked, then this function will suspend until that
 * > function has finished.
 *
 * ## Example Usage
 *
 * ```kotlin
 * stateContainer.update(newValue)
 * ```
 *
 * @param [stateValue] The value to change the [StateContainer.current] value to.
 */
public suspend fun <T> MutableStateContainer<T>.update(stateValue: T): Unit =
    this.update { stateValue }

/**
 * Resets the state to the provided [initialStateValue]. This provides a way to override what the initial value was by
 * providing an [initialStateValue] as a parameter.
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
 * stateContainer.reset(newInitialValue)
 * ```
 *
 * @param [initialStateValue] The value to set as the initial value. Defaults to [StateContainer.initial].
 */
public suspend fun <T> MutableStateContainer<T>.reset(initialStateValue: T) {
    this.reset { initialStateValue }
}

/**
 * Creates a [MutableStateContainer] with the provided [initialStateValue].
 *
 * @param [initialStateValue] The default [StateContainer.initial] value and the starting [StateContainer.current]
 * value. Note that this value can change over time with a [MutableStateContainer], if the
 * [MutableStateContainer.reset] function is invoked.
 *
 * @param [flowCoroutineScope] The [CoroutineScope] that is used to convert a [Flow] to a [StateFlow] for the
 * [StateContainer.current] property via the [Flow.stateIn] function.
 *
 * @param [emitDispatcher] The [CoroutineDispatcher] that is used to dispatch the changes to the states. Defaults to
 * [Dispatchers.Main]. This could be updated to [Dispatchers.Main] [MainCoroutineDispatcher.immediate] on supported
 * platforms.
 *
 * @param [flowDispatcher] The [CoroutineDispatcher] that is used to listen to the changes for the
 * [StateContainer.current] [StateFlow] property. This is typically used internally with a [Flow.flowOn] function call,
 * before [Flow.stateIn] usage, when combining the [upstreamFlow] and internal [MutableStateFlow] usage for the
 * [StateContainer.current] value.
 *
 * @param [sharingStarted] The strategy that controls when sharing is started and stopped. This value is used to
 * construct a [StateFlow] from the [upstreamFlow] function and the internal [MutableStateFlow].
 *
 * @param [upstreamFlow] The function that returns a initial [Flow] of values for the [StateContainer.current] property. This
 * is typically used in tandem with an internal [MutableStateFlow] and the [Flow.stateIn] function.
 *
 * ## Example Usage
 *
 * ```kotlin
 * val stateContainer = mutableStateContainerOf(
 *     initialStateValue = true,
 *     flowCoroutineScope = coroutineScope
 * )
 *
 * stateContainer.current.value // true
 * stateContainer.initial.value // true
 *
 * // Mutate the value
 * stateContainer.update(false)
 *
 * stateContainer.current.value // false
 * stateContainer.initial.value // true
 * ```
 */
public fun <T> mutableStateContainerOf(
    initialStateValue: T,
    flowCoroutineScope: CoroutineScope,
    emitDispatcher: CoroutineDispatcher = Dispatchers.Main,
    flowDispatcher: CoroutineDispatcher = emitDispatcher,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000),
    upstreamFlow: () -> Flow<T> = { emptyFlow() }
): MutableStateContainer<T> = DefaultMutableStateContainer(
    initialStateValue = initialStateValue,
    flowCoroutineScope = flowCoroutineScope,
    emitDispatcher = emitDispatcher,
    flowDispatcher = flowDispatcher,
    sharingStarted = sharingStarted,
    upstreamFlow = upstreamFlow
)

/**
 * Creates a [MutableStateContainer] with the provided [snapshot].
 *
 * @param [snapshot] The [StateContainer.SnapshotStateModel] value containing the values to use
 * initially for the returned [MutableStateContainer] instance.
 *
 * @param [flowCoroutineScope] The [CoroutineScope] that is used to convert a [Flow] to a [StateFlow] for the
 * [StateContainer.current] property via the [Flow.stateIn] function.
 *
 * @param [emitDispatcher] The [CoroutineDispatcher] that is used to dispatch the changes to the states. Defaults to
 * [Dispatchers.Main]. This could be updated to [Dispatchers.Main] [MainCoroutineDispatcher.immediate] on supported
 * platforms.
 *
 * @param [flowDispatcher] The [CoroutineDispatcher] that is used to listen to the changes for the
 * [StateContainer.current] [StateFlow] property. This is typically used internally with a [Flow.flowOn] function call,
 * before [Flow.stateIn] usage, when combining the [upstreamFlow] and internal [MutableStateFlow] usage for the
 * [StateContainer.current] value.
 *
 * @param [sharingStarted] The strategy that controls when sharing is started and stopped. This value is used to
 * construct a [StateFlow] from the [upstreamFlow] function and the internal [MutableStateFlow].
 *
 * @param [upstreamFlow] The function that returns a initial [Flow] of values for the [StateContainer.current] property. This
 * is typically used in tandem with an internal [MutableStateFlow] and the [Flow.stateIn] function.
 *
 * ## Example Usage
 *
 * ```kotlin
 * val initialStateContainer = mutableStateContainer(
 *     initialStateValue = true,
 *     flowCoroutineScope = coroutineScope
 * )
 * val snapshot = initialStateContainer.snapshot()
 * val stateContainer = mutableStateContainerOf(
 *     snapshot = snapshot,
 *     flowCoroutineScope = coroutineScope
 * )
 *
 * stateContainer.current.value // true
 * stateContainer.initial.value // true
 *
 * // Mutate the value
 * stateContainer.update(false)
 *
 * stateContainer.current.value // false
 * stateContainer.initial.value // true
 * ```
 */
public fun <T> mutableStateContainerOf(
    snapshot: StateContainer.SnapshotStateModel<T>,
    flowCoroutineScope: CoroutineScope,
    emitDispatcher: CoroutineDispatcher = Dispatchers.Main,
    flowDispatcher: CoroutineDispatcher = emitDispatcher,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000),
    upstreamFlow: () -> Flow<T> = { emptyFlow() }
): MutableStateContainer<T> = DefaultMutableStateContainer(
    initialStateValue = snapshot.initialStateValue,
    currentStateValue = snapshot.currentStateValue,
    flowCoroutineScope = flowCoroutineScope,
    emitDispatcher = emitDispatcher,
    flowDispatcher = flowDispatcher,
    sharingStarted = sharingStarted,
    upstreamFlow = upstreamFlow
)
