@file:Suppress("MemberVisibilityCanBePrivate")

package com.kodetools.statex.viewmodel

import com.kodetools.statex.container.MutableStateContainer
import com.kodetools.statex.container.StateContainer
import com.kodetools.statex.container.mutableStateContainerOf
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

/**
 * A design pattern level component that encapsulates state management and application logic for a user interface
 * component or concept, such as a "screen" within an application. A [ViewModel] follows the uni-directional data flow
 * (UDF) approach recommended by the Jetpack Compose documentation.
 *
 * A [ViewModel] exposes a single [StateFlow] of the wrapped state model values via the [uiState] property. This
 * [uiState] property provides a stream of state changes that occur as a result of the application logic within the
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
 * > When using Jetpack Compose or Compose Multiplatform, implementations of this interface must guarantee conformance
 * > to the Stable annotation requirements.
 *
 * ## Example Usage
 *
 * ```kotlin
 * class FeedViewModel : ViewModel(initialStateValue = FeedStateModel()) {
 *
 *    fun load() {
 *        viewModelScope.launch {
 *            uiState.mutable.update { current -> current.copy(isLoading = true) }
 *
 *            val items = withContext(Dispatchers.IO) {
 *                feedApi.load()
 *            }
 *
 *            uiState.mutable.update { current -> current.copy(isLoading = false, items = items) }
 *        }
 *    }
 * }
 * ```
 *
 * @param [initialStateValue] The initial state model value for the [ViewModel.uiState] [StateContainer].
 *
 * @param [emitDispatcher] The [CoroutineDispatcher] used to emit state models via the [MutableStateContainer.update]
 * function invocations.
 *
 * @param [flowDispatcher] The [CoroutineDispatcher] that is used to listen to the changes for the
 * [com.kodetools.statex.container.StateContainer.current] [StateFlow] property. This is typically used internally with a [Flow.flowOn] function call,
 * before [Flow.stateIn] usage.
 *
 * @param [sharingStarted] The strategy that controls when sharing is started and stopped. This value is used to
 * construct a [StateFlow] from the [ViewModel.uiStateUpstreamFlow] function and the internal [MutableStateFlow].
 */
public abstract class ViewModel<T : Any>(
    initialStateValue: T,
    private val emitDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val flowDispatcher: CoroutineDispatcher = emitDispatcher,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000)
) : PlatformViewModel() {

    /**
     * Represents the state container for managing UI state within the ViewModel.
     *
     * This property defines an instance of [ViewModelStateContainer], which is responsible for holding and managing
     * the state of type [T] throughout the lifecycle of the ViewModel. It utilizes a combination of declarative state
     * management and reactive flows, allowing state changes to be observed and updated efficiently.
     *
     * This container is tied to the [viewModelScope] of the ViewModel, ensuring proper lifecycle management. The
     * ability to mutate the state is restricted to [ViewModel] subclasses, while external consumers can only observe
     * the state.
     */
    public val uiState: ViewModelStateContainer<T> = viewModelStateContainerOf(
        initialStateValue = initialStateValue,
        emitDispatcher = emitDispatcher,
        flowDispatcher = flowDispatcher,
        sharingStarted = sharingStarted,
        upstreamFlow = ::uiStateUpstreamFlow
    )

    /**
     * Called during the initialization phase to produce a [Flow] of type [T].
     * The returned flow can be used to emit updates or manage state changes over time.
     *
     * @return A [Flow] of type [T], or an empty flow by default.
     */
    protected open fun uiStateUpstreamFlow(): Flow<T> = emptyFlow()

    /**
     * Obtains a [com.kodetools.statex.container.MutableStateContainer] instance for this [ViewModelStateContainer].
     *
     * This is a convenience property to access the internal [com.kodetools.statex.container.MutableStateContainer] [ViewModelStateContainer.delegate]
     * property. This approach was chosen to allow [com.kodetools.statex.viewmodel.ViewModel] subclasses to be able to
     * mutate their [com.kodetools.statex.container.StateContainer]s but only expose the read-only API.
     */
    protected val <T> ViewModelStateContainer<T>.mutable: MutableStateContainer<T>
        get() = this.delegate

    /**
     * Creates an instance of [ViewModelStateContainer] with the provided configuration values for state management and
     * flow handling.
     *
     * A [ViewModelStateContainer] is a [StateContainer] tied to the [viewModelScope] of the encapsulating [ViewModel],
     * and only exposes the [MutableStateContainer] via the protected property [mutable] so that only [ViewModel]
     * subclasses can mutate the state.
     *
     * @param [initialStateValue] The initial state value of type [T] that serves as the starting point for the
     * container.
     *
     * @param [emitDispatcher] The [CoroutineDispatcher] used for dispatching changes to the states. Defaults to
     * [Dispatchers.Main].
     *
     * @param [flowDispatcher] The [CoroutineDispatcher] used for observing and processing flow emissions internally.
     * Defaults to the value of [emitDispatcher].
     *
     * @param [sharingStarted] The strategy that defines how the state flow is started and stopped. Defaults to
     * [SharingStarted.WhileSubscribed] with a timeout of 5,000 milliseconds.
     *
     * @param [upstreamFlow] A lambda function that provides an initial [Flow] of values to be combined with internal
     * state management. Defaults to an empty flow.
     *
     * @return An instance of [ViewModelStateContainer] configured with the provided parameters.
     *
     * @see [ViewModelStateContainer]
     */
    protected fun viewModelStateContainerOf(
        initialStateValue: T,
        emitDispatcher: CoroutineDispatcher = Dispatchers.Main,
        flowDispatcher: CoroutineDispatcher = emitDispatcher,
        sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000),
        upstreamFlow: () -> Flow<T> = { emptyFlow() }
    ): ViewModelStateContainer<T> = ViewModelStateContainer(
        delegate = mutableStateContainerOf(
            initialStateValue = initialStateValue,
            flowCoroutineScope = viewModelScope,
            emitDispatcher = emitDispatcher,
            flowDispatcher = flowDispatcher,
            sharingStarted = sharingStarted,
            upstreamFlow = upstreamFlow
        )
    )

    /**
     * Creates an instance of [ViewModelStateContainer] with the provided configuration values for state management and
     * flow handling.
     *
     * A [ViewModelStateContainer] is a [StateContainer] tied to the [viewModelScope] of the encapsulating [ViewModel],
     * and only exposes the [MutableStateContainer] via the protected property [mutable] so that only [ViewModel]
     * subclasses can mutate the state.
     *
     * @param [snapshot] The [StateContainer.SnapshotStateModel] instance containing the initial and current state
     * values that serves as the starting point for the container.
     *
     * @param [emitDispatcher] The [CoroutineDispatcher] used for dispatching changes to the states. Defaults to
     * [Dispatchers.Main].
     *
     * @param [flowDispatcher] The [CoroutineDispatcher] used for observing and processing flow emissions internally.
     * Defaults to the value of [emitDispatcher].
     *
     * @param [sharingStarted] The strategy that defines how the state flow is started and stopped. Defaults to
     * [SharingStarted.WhileSubscribed] with a timeout of 5,000 milliseconds.
     *
     * @param [upstreamFlow] A lambda function that provides an initial [Flow] of values to be combined with internal
     * state management. Defaults to an empty flow.
     *
     * @return An instance of [ViewModelStateContainer] configured with the provided parameters.
     *
     * @see [ViewModelStateContainer]
     */
    protected fun viewModelStateContainerOf(
        snapshot: StateContainer.SnapshotStateModel<T>,
        emitDispatcher: CoroutineDispatcher = Dispatchers.Main,
        flowDispatcher: CoroutineDispatcher = emitDispatcher,
        sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000),
        upstreamFlow: () -> Flow<T> = { emptyFlow() }
    ): ViewModelStateContainer<T> = ViewModelStateContainer(
        delegate = mutableStateContainerOf(
            snapshot = snapshot,
            flowCoroutineScope = viewModelScope,
            emitDispatcher = emitDispatcher,
            flowDispatcher = flowDispatcher,
            sharingStarted = sharingStarted,
            upstreamFlow = upstreamFlow
        )
    )

    public companion object
}
