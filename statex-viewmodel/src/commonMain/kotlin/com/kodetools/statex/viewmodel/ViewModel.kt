@file:Suppress("MemberVisibilityCanBePrivate")

package com.kodetools.statex.viewmodel

import androidx.compose.runtime.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * A design pattern level component that encapsulates state management and application logic for a user interface
 * component or concept, such as a "screen" within an application. A [ViewModel] follows the uni-directional data flow
 * (UDF) approach recommended by the Jetpack Compose documentation.
 *
 * A [ViewModel] exposes a single [StateFlow] of the wrapped [State] model values via the [state] property. This
 * [state] property provides a stream of state changes that occur as a result of the application logic within the
 * [ViewModel] function, and can be subscribed to inside or outside the context of a `@Composable` function.
 *
 * Functions within a [ViewModel] should handle performing application logic, coordinating and invoking business logic,
 * mapping to the appropriate models, and emitting the updated state values as a result of those changes. Publicly
 * exposed functions will be invoked from the user interface components as a result of some action
 * (ex: user interaction). It is recommended to keep these functions as non-suspending functions, as a [ViewModel]
 * contains its own lifecycle and has a [CoroutineScope] that can be used to launch coroutines internally via the
 * [viewModelScope] property. This removes the need for the call-site to have to wrap the function invocations in a
 * coroutine scope themselves.
 *
 * > [!Note]
 * > Implementations of this interface must guarantee conformance to the [Stable] annotation requirements.
 *
 * ## Example Usage
 *
 * ```kotlin
 * class FeedViewModel : ViewModel(initialStateValue = FeedStateModel()) {
 *
 *    fun load() {
 *        viewModelScope.launch {
 *            emit { current -> current.copy(isLoading = true) }
 *
 *            val items = withContext(Dispatchers.IO) {
 *                feedApi.load()
 *            }
 *
 *            emit { current -> current.copy(isLoading = false, items = items) }
 *        }
 *    }
 * }
 * ```
 *
 * @param [initialStateValue] The initial state model value that will be emitted from the [ViewModel.state]
 * [StateFlow].
 *
 * @param [dispatcher] The [CoroutineDispatcher] used to emit state models via the [emit] functions.
 *
 * @param [sharingStarted] The strategy that controls when sharing is started and stopped. This value is used to
 * construct a [StateFlow] from the [ViewModel.onInit] function and the internal [MutableStateFlow].
 */
@Stable
public abstract class ViewModel<T : Any>(
    initialStateValue: T,
    private val dispatcher: MainCoroutineDispatcher = Dispatchers.Main,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000)
) : PlatformViewModel(),
    RememberObserver {

    /**
     * Called during the initialization phase to produce a [Flow] of type [T].
     * The returned flow can be used to emit updates or manage state changes over time.
     *
     * @return A [Flow] of type [T], or an empty flow by default.
     */
    protected open fun onInit(): Flow<T> = emptyFlow()

    private val mutableState = MutableStateFlow<T?>(null)

    /**
     * Represents the state managed by the ViewModel in the form of a [StateFlow].
     *
     * This state is constructed by merging the initial state flow produced by [onInit] and a mutable
     * state flow that holds non-null values. The resulting flow is scoped to the [viewModelScope]
     * and starts sharing its updates as determined by the `sharingStarted` parameter. It initializes
     * with the value provided by `initialStateValue`.
     *
     * The [state] flow updates dynamically to reflect state changes triggered through internal mechanisms
     * and can be observed to react to state transitions accordingly.
     *
     * It effectively combines initialization logic, mutable state management, and lifetime scoping
     * to provide a reactive and consistent model of the ViewModel's state.
     */
    public val state: StateFlow<T> = merge(
        onInit(),
        mutableState.filterNotNull()
    ).stateIn(
        scope = viewModelScope,
        started = sharingStarted,
        initialValue = initialStateValue
    )

    private val mutex = Mutex(locked = false)

    override fun onRemembered() {}

    override fun onForgotten() {}

    override fun onAbandoned() {}

    /**
     * Emits an updated state value by applying the given transformation to the current state.
     *
     * The function ensures thread-safety using a mutex lock and updates the state in a manner
     * that is compatible with Compose's State management.
     *
     * @param [block] A suspending function that takes the current state of type [T] as input
     * and returns the updated state of type [T].
     */
    @Suppress("MemberVisibilityCanBePrivate")
    protected suspend fun emit(block: suspend (current: T) -> T) {
        mutex.withLock {
            val currentValue = state.value
            val updatedValue = block.invoke(currentValue)

            if (updatedValue != currentValue) {
                // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
                withContext(dispatcher) {
                    mutableState.value = updatedValue
                }
            }
        }
    }

    /**
     * Emits a new state value by directly setting the provided value.
     *
     * This function is a shortcut for updating the state to a specific value
     * without needing a transformation block. It ensures thread-safe updates
     * to the state and integrates seamlessly with Compose's State management.
     *
     * @param [value] The new state value of type [T] to be emitted.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    protected suspend fun emit(value: T): Unit = emit { value }

    public companion object
}
