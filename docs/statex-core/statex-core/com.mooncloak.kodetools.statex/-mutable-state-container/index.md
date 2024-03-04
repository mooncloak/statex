//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[MutableStateContainer](index.md)

# MutableStateContainer

@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

interface [MutableStateContainer](index.md)&lt;[T](index.md)&gt; : [StateContainer](../-state-container/index.md)&lt;[T](index.md)&gt; 

A [StateContainer](../-state-container/index.md) that provides the ability to mutate the wrapped state values. All mutable operations should be considered thread-safe and safe to access concurrently.

!Note Implementations of this interface must perform mutation operations in a concurrency-safe manner.

#### See also

| | |
|---|---|
| [mutableStateContainerOf](../mutable-state-container-of.md) | To create an instance of this interface. |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [changed](../-state-container/changed.md) | [common]<br>abstract val [changed](../-state-container/changed.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>A [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) that determines whether the [current](../-state-container/current.md) value ever changed from the [initial](../-state-container/initial.md) value. Once the [current](../-state-container/current.md) value changes from the [initial](../-state-container/initial.md) value, this should always return a [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) with a value of `true`, even if the [current](../-state-container/current.md) value changed back to the same value of [initial](../-state-container/initial.md), until it is reset. |
| [current](../-state-container/current.md) | [common]<br>abstract val [current](../-state-container/current.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt;<br>The current [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) for this component. This value can change over time, so subsequent calls to access this property can return different values. |
| [initial](../-state-container/initial.md) | [common]<br>abstract val [initial](../-state-container/initial.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt;<br>The initial [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) when this component was first created or when it was last reset. This value does not change when the [current](../-state-container/current.md) value changes. However, this value may change depending on the [StateContainer](../-state-container/index.md) implementation. |
| [stream](../-state-container/stream.md) | [common]<br>abstract val [stream](../-state-container/stream.md): StateFlow&lt;[T](index.md)&gt;<br>A Flow of changes that occur to the [current](../-state-container/current.md) value over time. Whenever the [current](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) value changes, that new value is emitted through this Flow. This is a StateFlow which means that it can retain the last emitted value which can be accessed like so: `container.stream.value`. |

## Functions

| Name | Summary |
|---|---|
| [change](change.md) | [common]<br>abstract suspend fun [change](change.md)(block: suspend (current: [T](index.md)) -&gt; [T](index.md))<br>Updates the [current](../../../../statex-core/com.mooncloak.kodetools.statex/-mutable-state-container/current.md) value to be the value obtained by invoking the provided [block](change.md) function. The [block](change.md) function is invoked within a lock, so subsequent calls to read the contained state values, such as [current](../../../../statex-core/com.mooncloak.kodetools.statex/-mutable-state-container/current.md), are guaranteed to return the same value, as no other mutations can occur until after the function returns and its result is emitted. |
| [change](../change.md) | [common]<br>suspend fun &lt;[T](../change.md)&gt; [MutableStateContainer](index.md)&lt;[T](../change.md)&gt;.[change](../change.md)(value: [T](../change.md))<br>Updates the [StateContainer.current](../-state-container/current.md) value to be the provided [value](../change.md). This is a convenience function that delegates to the [MutableStateContainer.change](change.md) by providing a higher-order function that simply returns the provided [value](../change.md). |
| [reset](reset.md) | [common]<br>abstract suspend fun [reset](reset.md)(initialValue: [T](index.md) = this.initial.value)<br>Resets the state to the provided [initialValue](reset.md). This provides a way to override what the initial value was by providing an [initialValue](reset.md) as a parameter. |
