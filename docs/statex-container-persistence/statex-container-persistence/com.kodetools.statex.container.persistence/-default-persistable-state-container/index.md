//[statex-container-persistence](../../../index.md)/[com.kodetools.statex.container.persistence](../index.md)/[DefaultPersistableStateContainer](index.md)

# DefaultPersistableStateContainer

[common]\
@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

class [DefaultPersistableStateContainer](index.md)&lt;[T](index.md)&gt; : [PersistableStateContainer](../-persistable-state-container/index.md)&lt;[T](index.md)&gt;

## Properties

| Name | Summary |
|---|---|
| [current](current.md) | [common]<br>open override val [current](current.md): StateFlow&lt;[T](index.md)&gt; |
| [initial](initial.md) | [common]<br>open override val [initial](initial.md): StateFlow&lt;[T](index.md)&gt; |
| [policy](policy.md) | [common]<br>val [policy](policy.md): [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html)&lt;[T](index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [common]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [common]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |
| [reset](reset.md) | [common]<br>open suspend override fun [reset](reset.md)(block: suspend ([T](index.md)) -&gt; [T](index.md)) |
| [snapshot](snapshot.md) | [common]<br>open suspend override fun [snapshot](snapshot.md)(): [StateContainer.SnapshotStateModel](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt; |
| [toString](to-string.md) | [common]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [update](update.md) | [common]<br>open suspend override fun [update](update.md)(block: suspend (current: [T](index.md)) -&gt; [T](index.md)) |
