//[statex-container](../../../index.md)/[com.kodetools.statex.container](../index.md)/[DefaultMutableStateContainer](index.md)

# DefaultMutableStateContainer

[common]\
class [DefaultMutableStateContainer](index.md)&lt;[T](index.md)&gt; : [MutableStateContainer](../-mutable-state-container/index.md)&lt;[T](index.md)&gt; 

A default [MutableStateContainer](../-mutable-state-container/index.md) implementation.

## Properties

| Name | Summary |
|---|---|
| [current](current.md) | [common]<br>open override val [current](current.md): StateFlow&lt;[T](index.md)&gt;<br>The StateFlow of the current value of this [StateContainer](../-state-container/index.md). |
| [initial](initial.md) | [common]<br>open override val [initial](initial.md): StateFlow&lt;[T](index.md)&gt;<br>The StateFlow of the initial value when this component was first created or when it was last reset. This value does not change when the [current](current.md) value changes via [MutableStateContainer.update](../-mutable-state-container/update.md) function calls. However, this value may change on invocations of the [MutableStateContainer.reset](../-mutable-state-container/reset.md) function. |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [common]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [common]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |
| [reset](reset.md) | [common]<br>open suspend override fun [reset](reset.md)(block: suspend (initial: [T](index.md)) -&gt; [T](index.md))<br>Resets the state to the value obtained from invoking the provided [block](reset.md) function. |
| [reset](../reset.md) | [common]<br>suspend fun &lt;[T](../reset.md)&gt; [MutableStateContainer](../-mutable-state-container/index.md)&lt;[T](../reset.md)&gt;.[reset](../reset.md)(initialStateValue: [T](../reset.md))<br>Resets the state to the provided [initialStateValue](../reset.md). This provides a way to override what the initial value was by providing an [initialStateValue](../reset.md) as a parameter. |
| [snapshot](snapshot.md) | [common]<br>open suspend override fun [snapshot](snapshot.md)(): [StateContainer.SnapshotStateModel](../-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt;<br>Creates a SnapshotStateModel of all the current values within this [StateContainer](../-state-container/index.md). |
| [toString](to-string.md) | [common]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [update](update.md) | [common]<br>open suspend override fun [update](update.md)(block: suspend (current: [T](index.md)) -&gt; [T](index.md))<br>Updates the [current](current.md) value to be the value obtained by invoking the provided [block](update.md) function. The [block](update.md) function is invoked within a lock, so subsequent calls to read the contained state values, such as [current](current.md), are guaranteed to return the same value, as no other mutations can occur until after the function returns and its result is emitted. |
| [update](../update.md) | [common]<br>suspend fun &lt;[T](../update.md)&gt; [MutableStateContainer](../-mutable-state-container/index.md)&lt;[T](../update.md)&gt;.[update](../update.md)(stateValue: [T](../update.md))<br>Updates the [StateContainer.current](../-state-container/current.md) value to be the provided [stateValue](../update.md). This is a convenience function that delegates to the [MutableStateContainer.update](../-mutable-state-container/update.md) by providing a higher-order function that simply returns the provided [stateValue](../update.md). |
