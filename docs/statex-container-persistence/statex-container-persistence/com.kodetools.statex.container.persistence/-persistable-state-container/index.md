//[statex-container-persistence](../../../index.md)/[com.kodetools.statex.container.persistence](../index.md)/[PersistableStateContainer](index.md)

# PersistableStateContainer

interface [PersistableStateContainer](index.md)&lt;[T](index.md)&gt; : [MutableStateContainer](../../../../statex-container/statex-container/com.kodetools.statex.container/-mutable-state-container/index.md)&lt;[T](index.md)&gt; 

A [MutableStateContainer](../../../../statex-container/statex-container/com.kodetools.statex.container/-mutable-state-container/index.md) that persists its values to storage.

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
| [current](index.md#2035780147%2FProperties%2F1919035847) | [common]<br>abstract val [current](index.md#2035780147%2FProperties%2F1919035847): StateFlow&lt;[T](index.md)&gt; |
| [initial](index.md#805632712%2FProperties%2F1919035847) | [common]<br>abstract val [initial](index.md#805632712%2FProperties%2F1919035847): StateFlow&lt;[T](index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [reset](index.md#-1811025550%2FFunctions%2F1919035847) | [common]<br>abstract suspend fun [reset](index.md#-1811025550%2FFunctions%2F1919035847)(block: suspend (initialStateValue: [T](index.md)) -&gt; [T](index.md)) |
| [snapshot](index.md#1326224010%2FFunctions%2F1919035847) | [common]<br>abstract suspend fun [snapshot](index.md#1326224010%2FFunctions%2F1919035847)(): [StateContainer.SnapshotStateModel](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt; |
| [update](index.md#771554270%2FFunctions%2F1919035847) | [common]<br>abstract suspend fun [update](index.md#771554270%2FFunctions%2F1919035847)(block: suspend (currentStateValue: [T](index.md)) -&gt; [T](index.md)) |
