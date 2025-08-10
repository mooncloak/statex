//[statex-container](../../../index.md)/[com.kodetools.statex.container](../index.md)/[MutableStateContainer](index.md)

# MutableStateContainer

interface [MutableStateContainer](index.md)&lt;[T](index.md)&gt; : [StateContainer](../-state-container/index.md)&lt;[T](index.md)&gt; 

A [StateContainer](../-state-container/index.md) that provides the ability to mutate the wrapped state values. All mutable operations should be considered thread-safe and safe to access concurrently.

!Note Implementations of this interface must perform mutation operations in a concurrency-safe manner.

#### See also

| | |
|---|---|
| [mutableStateContainerOf](../mutable-state-container-of.md) | To create an instance of this interface. |

#### Inheritors

| |
|---|
| [DefaultMutableStateContainer](../-default-mutable-state-container/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [current](../-state-container/current.md) | [common]<br>abstract val [current](../-state-container/current.md): StateFlow&lt;[T](index.md)&gt;<br>The StateFlow of the current value of this [StateContainer](../-state-container/index.md). |
| [initial](../-state-container/initial.md) | [common]<br>abstract val [initial](../-state-container/initial.md): StateFlow&lt;[T](index.md)&gt;<br>The StateFlow of the initial value when this component was first created or when it was last reset. This value does not change when the [current](../-state-container/current.md) value changes via [MutableStateContainer.update](update.md) function calls. However, this value may change on invocations of the [MutableStateContainer.reset](reset.md) function. |

## Functions

| Name | Summary |
|---|---|
| [reset](reset.md) | [common]<br>abstract suspend fun [reset](reset.md)(block: suspend (initialStateValue: [T](index.md)) -&gt; [T](index.md) = { it })<br>Resets the state to the value obtained from invoking the provided [block](reset.md) function. |
| [reset](../reset.md) | [common]<br>suspend fun &lt;[T](../reset.md)&gt; [MutableStateContainer](index.md)&lt;[T](../reset.md)&gt;.[reset](../reset.md)(initialStateValue: [T](../reset.md))<br>Resets the state to the provided [initialStateValue](../reset.md). This provides a way to override what the initial value was by providing an [initialStateValue](../reset.md) as a parameter. |
| [snapshot](../-state-container/snapshot.md) | [common]<br>abstract suspend fun [snapshot](../-state-container/snapshot.md)(): [StateContainer.SnapshotStateModel](../-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt;<br>Creates a [SnapshotStateModel](../-state-container/-snapshot-state-model/index.md) of all the current values within this [StateContainer](../-state-container/index.md). |
| [update](update.md) | [common]<br>abstract suspend fun [update](update.md)(block: suspend (currentStateValue: [T](index.md)) -&gt; [T](index.md))<br>Updates the [current](../../../../statex-container/com.kodetools.statex.container/-mutable-state-container/current.md) value to be the value obtained by invoking the provided [block](update.md) function. The [block](update.md) function is invoked within a lock, so subsequent calls to read the contained state values, such as [current](../../../../statex-container/com.kodetools.statex.container/-mutable-state-container/current.md), are guaranteed to return the same value, as no other mutations can occur until after the function returns and its result is emitted. |
| [update](../update.md) | [common]<br>suspend fun &lt;[T](../update.md)&gt; [MutableStateContainer](index.md)&lt;[T](../update.md)&gt;.[update](../update.md)(stateValue: [T](../update.md))<br>Updates the [StateContainer.current](../-state-container/current.md) value to be the provided [stateValue](../update.md). This is a convenience function that delegates to the [MutableStateContainer.update](update.md) by providing a higher-order function that simply returns the provided [stateValue](../update.md). |
