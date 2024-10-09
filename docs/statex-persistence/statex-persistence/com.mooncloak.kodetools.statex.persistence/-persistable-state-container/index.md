//[statex-persistence](../../../index.md)/[com.mooncloak.kodetools.statex.persistence](../index.md)/[PersistableStateContainer](index.md)

# PersistableStateContainer

interface [PersistableStateContainer](index.md)&lt;[T](index.md)&gt; : [MutableStateContainer](../../../../statex-core/statex-core/com.mooncloak.kodetools.statex/-mutable-state-container/index.md)&lt;[T](index.md)&gt; 

#### Inheritors

| |
|---|
| [DefaultPersistableStateContainer](../-default-persistable-state-container/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [changed](index.md#334710436%2FProperties%2F1961133779) | [common]<br>abstract val [changed](index.md#334710436%2FProperties%2F1961133779): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [current](index.md#340337535%2FProperties%2F1961133779) | [common]<br>abstract val [current](index.md#340337535%2FProperties%2F1961133779): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt; |
| [flow](index.md#1541451028%2FProperties%2F1961133779) | [common]<br>open val [flow](index.md#1541451028%2FProperties%2F1961133779): StateFlow&lt;[T](index.md)&gt; |
| [initial](index.md#-889809900%2FProperties%2F1961133779) | [common]<br>abstract val [initial](index.md#-889809900%2FProperties%2F1961133779): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt; |
| [stream](index.md#561521090%2FProperties%2F1961133779) | [common]<br>abstract val [~~stream~~](index.md#561521090%2FProperties%2F1961133779): StateFlow&lt;[T](index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [change](index.md#-2118345095%2FFunctions%2F1961133779) | [common]<br>abstract suspend fun [~~change~~](index.md#-2118345095%2FFunctions%2F1961133779)(block: suspend (current: [T](index.md)) -&gt; [T](index.md)) |
| [reset](index.md#801414195%2FFunctions%2F1961133779) | [common]<br>abstract suspend fun [reset](index.md#801414195%2FFunctions%2F1961133779)(initialValue: [T](index.md)) |
| [snapshot](index.md#307110590%2FFunctions%2F1961133779) | [common]<br>abstract suspend fun [snapshot](index.md#307110590%2FFunctions%2F1961133779)(): [StateContainer.SnapshotStateModel](../../../../statex-core/statex-core/com.mooncloak.kodetools.statex/-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt; |
| [update](index.md#1467721746%2FFunctions%2F1961133779) | [common]<br>open suspend fun [update](index.md#1467721746%2FFunctions%2F1961133779)(block: suspend (current: [T](index.md)) -&gt; [T](index.md)) |
