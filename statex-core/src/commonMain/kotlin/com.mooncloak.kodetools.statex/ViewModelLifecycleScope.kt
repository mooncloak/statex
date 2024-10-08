package com.mooncloak.kodetools.statex

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State

/**
 * Defines the lifecycle for a [ViewModel].
 *
 * > [!Note]
 * > Implementations of this interface must guarantee conformance to the [Stable] annotation requirements.
 */
@Stable
public sealed interface ViewModelLifecycleScope {

    /**
     * A [State] determining whether the [ViewModel] component containing this lifecycle, is
     * currently bound to the lifecycle of a containing component, such as a Composable function.
     * If the [State.value] is `true`, then it is safe to invoke operations on the [ViewModel],
     * otherwise, no operations on the [ViewModel] should be invoked because they will not emit the
     * state values correctly to any subscribers.
     */
    public val isBound: State<Boolean>

    /**
     * Bind this [ViewModel] to the scope of the calling containing component. If the [ViewModel]
     * with this [ViewModelLifecycleScope] is already bound, then this function will do nothing.
     */
    public fun bind()

    /**
     * Unbind this [ViewModel] from the scope of the calling containing component. If the
     * [ViewModel] with this [ViewModelLifecycleScope] is already not bound, then this function
     * will do nothing.
     */
    public fun unbind()

    public companion object
}
