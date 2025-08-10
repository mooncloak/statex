//[statex-container](../../../../index.md)/[com.kodetools.statex.container](../../index.md)/[StateContainer](../index.md)/[SnapshotStateModel](index.md)

# SnapshotStateModel

[common]\
data class [SnapshotStateModel](index.md)&lt;[T](index.md)&gt;(val initialStateValue: [T](index.md), val currentStateValue: [T](index.md))

Represents the data associated with the current state of a [StateContainer](../index.md) at a particular instance.

## Constructors

| | |
|---|---|
| [SnapshotStateModel](-snapshot-state-model.md) | [common]<br>constructor(initialStateValue: [T](index.md), currentStateValue: [T](index.md)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [currentStateValue](current-state-value.md) | [common]<br>val [currentStateValue](current-state-value.md): [T](index.md)<br>Corresponds to the [StateContainer.current](../current.md) value at the moment this snapshot was taken. |
| [initialStateValue](initial-state-value.md) | [common]<br>val [initialStateValue](initial-state-value.md): [T](index.md)<br>Corresponds to the [StateContainer.initial](../initial.md) value at the moment this snapshot was taken. |
