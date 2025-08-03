@file:Suppress("MemberVisibilityCanBePrivate")

package com.mooncloak.kodetools.statex

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
 * component or concept, such as a screen within an application. A [ViewModel] follows the uni-directional data flow
 * (UDF) approach recommended by the Jetpack Compose documentation.
 *
 * A [ViewModel] exposes a single [StateContainer] of the wrapped [State] values via the [state] property. This [state]
 * property provides a stream of state changes that occur as a result of the application logic within the [ViewModel]
 * function, and can be subscribed to inside or outside the context of a `@Composable` function.
 *
 * Functions within a [ViewModel] should handle performing application logic, coordinating and invoking business logic,
 * mapping to the appropriate models, and emitting the updated state values as a result of those changes. Publicly
 * exposed functions will be invoked from the user interface components as a result of some action
 * (ex: user interaction). It is recommended to keep these functions as non-suspending functions, as a [ViewModel]
 * contains its own lifecycle and has a [coroutineScope] that can be used to launch coroutines internally. This removes
 * the need for the call-site to have to wrap the function invocations in a coroutine scope themselves.
 *
 * > [!Note]
 * > A [ViewModel] has its own lifecycle and must be bound to the user interface component (ex: `@Composable` function,
 * > View, etc.) for it to work correctly. By default, a [ViewModel] is bound to the [remember] lifecycle since it
 * > implements the [RememberObserver] interface. If this behavior is not desired, simply pass `bindOnRemember=false`
 * > to the [ViewModel] constructor.
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
 *        coroutineScope.launch {
 *            emit(value = state.current.value.copy(isLoading = true))
 *
 *            val items = withContext(Dispatchers.IO) {
 *                feedApi.load()
 *            }
 *
 *            emit(value = state.current.value.copy(isLoading = false, items = items))
 *        }
 *    }
 * }
 * ```
 *
 * @param [initialStateValue] The initial value to provide to the [mutableStateContainer] function when constructing
 * the [StateContainer] instance for the [state] property.
 */
@Stable
public abstract class ViewModel<T>(
    initialStateValue: T,
    private val dispatcher: MainCoroutineDispatcher = Dispatchers.Main,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000)
) : PlatformViewModel(),
    RememberObserver {

    protected open fun onInit(): Flow<T> = emptyFlow()

    private val mutableState = MutableStateFlow<T?>(null)

    /**
     * Provides access to the read-only [StateContainer] values. [ViewModel] implementations can mutate the wrapped
     * state by emitting new state values via the protected [emit] and [reset] functions.
     *
     * ## Example Usage:
     *
     * ```kotlin
     * @Composable
     * fun FeedScreen(
     *     viewModel: FeedViewModel,
     *     modifier: Modifier = Modifier
     * ) {
     *     // Retrieve and use the compose State<T> value
     *     val currentState by viewModel.state.current
     *
     *     AnimatedVisibility(
     *         visible = currentState.isLoading // Example usage of the underlying state value
     *     ) { ... }
     * }
     * ```
     *
     * @see [StateContainer]
     */
    public val state: StateFlow<T> = merge(
        onInit(),
        mutableState.filterNotNull()
    ).stateIn(
        scope = viewModelScope,
        started = sharingStarted,
        initialValue = initialStateValue
    )

    private val mutex = Mutex(locked = true)

    override fun onRemembered() {
    }

    override fun onForgotten() {
    }

    override fun onAbandoned() {
    }

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

    @Suppress("MemberVisibilityCanBePrivate")
    protected suspend fun emit(value: T) {
        mutex.withLock {
            val currentValue = state.value

            if (value != currentValue) {
                // Update from the appropriate thread for Compose State to make sure that it gets handled correctly.
                withContext(dispatcher) {
                    mutableState.value = value
                }
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected suspend fun reset(initialValue: T = this.state.value) {
        mutex.withLock {
            TODO()
        }
    }

    public companion object
}
