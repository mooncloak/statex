//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[ViewModel](index.md)/[isBound](is-bound.md)

# isBound

[common]\
open override val [isBound](is-bound.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;

A [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) determining whether the [ViewModel](index.md) component containing this lifecycle, is currently bound to the lifecycle of a containing component, such as a Composable function. If the [State.value](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html#value--) is `true`, then it is safe to invoke operations on the [ViewModel](index.md), otherwise, no operations on the [ViewModel](index.md) should be invoked because they will not emit the state values correctly to any subscribers.
