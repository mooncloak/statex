//[statex-persistence](../../../index.md)/[com.mooncloak.kodetools.statex.persistence](../index.md)/[DefaultPersistableStateContainer](index.md)

# DefaultPersistableStateContainer

[common]\
@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

class [DefaultPersistableStateContainer](index.md)&lt;[T](index.md)&gt; : [PersistableStateContainer](../-persistable-state-container/index.md)&lt;[T](index.md)&gt;

## Properties

| Name | Summary |
|---|---|
| [changed](changed.md) | [common]<br>open override val [changed](changed.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [current](current.md) | [common]<br>open override val [current](current.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt; |
| [flow](../-persistable-state-container/index.md#1541451028%2FProperties%2F1961133779) | [common]<br>open val [flow](../-persistable-state-container/index.md#1541451028%2FProperties%2F1961133779): StateFlow&lt;[T](index.md)&gt; |
| [initial](initial.md) | [common]<br>open override val [initial](initial.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt; |
| [policy](policy.md) | [common]<br>val [policy](policy.md): [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html)&lt;[T](index.md)&gt; |
| [stream](stream.md) | [common]<br>open override val [stream](stream.md): StateFlow&lt;[T](index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [change](change.md) | [common]<br>open suspend override fun [change](change.md)(block: suspend (current: [T](index.md)) -&gt; [T](index.md)) |
| [equals](equals.md) | [common]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [common]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [reset](reset.md) | [common]<br>open suspend override fun [reset](reset.md)(initialValue: [T](index.md)) |
| [snapshot](snapshot.md) | [common]<br>open suspend override fun [snapshot](snapshot.md)(): [StateContainer.SnapshotStateModel](../../../../statex-core/statex-core/com.mooncloak.kodetools.statex/-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt; |
| [toString](to-string.md) | [common]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [update](../-persistable-state-container/index.md#1467721746%2FFunctions%2F1961133779) | [common]<br>open suspend fun [update](../-persistable-state-container/index.md#1467721746%2FFunctions%2F1961133779)(block: suspend (current: [T](index.md)) -&gt; [T](index.md)) |
