//[statex-container](../../../index.md)/[com.kodetools.statex.container](../index.md)/[StateContainer](index.md)

# StateContainer

interface [StateContainer](index.md)&lt;[T](index.md)&gt;

A generic container around StateFlow values. This encapsulates the current, while also retaining the initial value.

Instead of using a StateFlow directly, one could use the [StateContainer](index.md) and [MutableStateContainer](../-mutable-state-container/index.md) components to encapsulate additional associated state, and handle mutations to the underlying value in a concurrency-safe manner.

#### See also

| | |
|---|---|
| [MutableStateContainer](../-mutable-state-container/index.md) | for a mutable version of this [StateContainer](index.md) interface. |
| [mutableStateContainerOf](../mutable-state-container-of.md) | To create a [MutableStateContainer](../-mutable-state-container/index.md) instance. |

#### Inheritors

| |
|---|
| [MutableStateContainer](../-mutable-state-container/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |
| [SnapshotStateModel](-snapshot-state-model/index.md) | [common]<br>data class [SnapshotStateModel](-snapshot-state-model/index.md)&lt;[T](-snapshot-state-model/index.md)&gt;(val initialStateValue: [T](-snapshot-state-model/index.md), val currentStateValue: [T](-snapshot-state-model/index.md))<br>Represents the data associated with the current state of a [StateContainer](index.md) at a particular instance. |

## Properties

| Name | Summary |
|---|---|
| [current](current.md) | [common]<br>abstract val [current](current.md): StateFlow&lt;[T](index.md)&gt;<br>The StateFlow of the current value of this [StateContainer](index.md). |
| [initial](initial.md) | [common]<br>abstract val [initial](initial.md): StateFlow&lt;[T](index.md)&gt;<br>The StateFlow of the initial value when this component was first created or when it was last reset. This value does not change when the [current](current.md) value changes via [MutableStateContainer.update](../-mutable-state-container/update.md) function calls. However, this value may change on invocations of the [MutableStateContainer.reset](../-mutable-state-container/reset.md) function. |

## Functions

| Name | Summary |
|---|---|
| [snapshot](snapshot.md) | [common]<br>abstract suspend fun [snapshot](snapshot.md)(): [StateContainer.SnapshotStateModel](-snapshot-state-model/index.md)&lt;[T](index.md)&gt;<br>Creates a [SnapshotStateModel](-snapshot-state-model/index.md) of all the current values within this [StateContainer](index.md). |
