//[statex-core](../../../../index.md)/[com.mooncloak.kodetools.statex](../../index.md)/[StateContainer](../index.md)/[SnapshotStateModel](index.md)

# SnapshotStateModel

[common]\
@[Immutable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Immutable.html)

@Serializable

data class [SnapshotStateModel](index.md)&lt;[T](index.md)&gt;(val initial: [T](index.md), val current: [T](index.md), val changed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

Represents the data associated with the current state of a [StateContainer](../index.md) at a particular instance.

## Constructors

| | |
|---|---|
| [SnapshotStateModel](-snapshot-state-model.md) | [common]<br>constructor(initial: [T](index.md), current: [T](index.md), changed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [changed](changed.md) | [common]<br>@SerialName(value = &quot;changed&quot;)<br>val [changed](changed.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Corresponds to the [StateContainer.changed](../changed.md) value at the moment this snapshot was taken. |
| [current](current.md) | [common]<br>@SerialName(value = &quot;current&quot;)<br>val [current](current.md): [T](index.md)<br>Corresponds to the [StateContainer.current](../current.md) value at the moment this snapshot was taken. |
| [initial](initial.md) | [common]<br>@SerialName(value = &quot;initial&quot;)<br>val [initial](initial.md): [T](index.md)<br>Corresponds to the [StateContainer.initial](../initial.md) value at the moment this snapshot was taken. |
