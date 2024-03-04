//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[ViewModelLifecycleScope](index.md)

# ViewModelLifecycleScope

@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

interface [ViewModelLifecycleScope](index.md)

Defines the lifecycle for a [ViewModel](../-view-model/index.md).

!Note Implementations of this interface must guarantee conformance to the [Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html) annotation requirements.

#### Inheritors

| |
|---|
| [ViewModel](../-view-model/index.md) |

## Properties

| Name | Summary |
|---|---|
| [isBound](is-bound.md) | [common]<br>abstract val [isBound](is-bound.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>A [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) determining whether the [ViewModel](../-view-model/index.md) component containing this lifecycle, is currently bound to the lifecycle of a containing component, such as a Composable function. If the [State.value](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html#value--) is `true`, then it is safe to invoke operations on the [ViewModel](../-view-model/index.md), otherwise, no operations on the [ViewModel](../-view-model/index.md) should be invoked because they will not emit the state values correctly to any subscribers. |

## Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | [common]<br>abstract fun [bind](bind.md)()<br>Bind this [ViewModel](../-view-model/index.md) to the scope of the calling containing component. If the [ViewModel](../-view-model/index.md) with this [ViewModelLifecycleScope](index.md) is already bound, then this function will do nothing. |
| [unbind](unbind.md) | [common]<br>abstract fun [unbind](unbind.md)()<br>Unbind this [ViewModel](../-view-model/index.md) from the scope of the calling containing component. If the [ViewModel](../-view-model/index.md) with this [ViewModelLifecycleScope](index.md) is already not bound, then this function will do nothing. |
