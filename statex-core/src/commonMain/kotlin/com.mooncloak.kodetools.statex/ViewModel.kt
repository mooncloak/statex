@file:Suppress("MemberVisibilityCanBePrivate")

package com.mooncloak.kodetools.statex

import androidx.compose.runtime.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.CoroutineContext

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
    policy: SnapshotMutationPolicy<T> = structuralEqualityPolicy(),
    dispatcher: MainCoroutineDispatcher = Dispatchers.Main,
    private val bindOnRemember: Boolean = true
) : PlatformViewModel(),
    ViewModelLifecycleScope,
    RememberObserver {

    override val isBound: State<Boolean>
        get() = mutableIsBound

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
    public val state: StateContainer<T>
        get() = mutableStateContainer

    private val internalCoroutineScope: CoroutineScope = object : CoroutineScope {

        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.Main
    }

    /**
     * A [CoroutineScope] bound to this [ViewModel]'s lifecycle defined by when the component [isBound]. This can be
     * used from within [ViewModel] implementations functions to launch coroutines.
     */
    protected override val coroutineScope: CoroutineScope
        get() = super.coroutineScope ?: internalCoroutineScope

    private lateinit var job: Job

    private val mutableStateContainer = mutableStateContainerOf(
        initialValue = initialStateValue,
        policy = policy,
        dispatcher = dispatcher
    )

    private val mutableIsBound = mutableStateOf(false)

    // The Mutex for all operations to ensure that they are all concurrency-safe. The Mutex is
    // initially locked until the lifecycle is bound, to prevent mutation operations from occurring
    // while the ViewModel is not bound and causing exceptions to be thrown or other undefined
    // behavior.
    private val mutex = Mutex(locked = true)

    override fun onRemembered() {
        if (bindOnRemember) {
            bind()
        }
    }

    override fun onForgotten() {
        if (bindOnRemember) {
            unbind()
        }
    }

    override fun onAbandoned() {
        if (bindOnRemember) {
            unbind()
        }
    }

    override fun bind() {
        if (!isBound.value) {
            // Prevent any operations from being invoked while we are binding. Once, we are finished
            // binding, we can unlock and allow any operations to occur.
            mutex.tryLock()

            if (::job.isInitialized && job.isActive) {
                // Cancel any existing job. The job should be properly cancelled in the unbind
                // function, but if for some reason, it didn't get cancelled, make sure we cancel
                // it here.
                job.cancel("Creating new Job in ${ViewModel::class.simpleName} ${ViewModel<*>::bind::name} function.")
            }

            job = SupervisorJob()

            // Unlock the Mutex so that other operations can be performed, now that this ViewModel
            // is bound to a lifecycle.
            mutex.unlock()

            mutableIsBound.value = true

            onBind()
        }
    }

    override fun unbind() {
        if (isBound.value) {
            onUnbind()

            job.cancel()

            // Lock the Mutex so that mutation operations are not performed while this component is
            // not bound.
            mutex.tryLock()

            mutableIsBound.value = false
        }
    }

    /**
     * Invoked when this component is bound. This can be useful for startup operations. Remember to invoke the super
     * implementation as there may be super class logic that needs to be invoked.
     */
    protected open fun onBind() {}

    /**
     * Invoked when this component is unbound. This can be useful for cleanup operations. Remember to invoke the super
     * implementation as there may be super class logic that needs to be invoked.
     */
    protected open fun onUnbind() {}

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun emit(block: suspend (current: T) -> T) {
        coroutineScope.launch {
            mutex.withLock {
                mutableStateContainer.update(block = block)
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun emit(value: T) {
        coroutineScope.launch {
            mutex.withLock {
                mutableStateContainer.update(value = value)
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun reset(initialValue: T = this.state.initial.value) {
        coroutineScope.launch {
            mutex.withLock {
                mutableStateContainer.reset(initialValue = initialValue)
            }
        }
    }

    public companion object
}
