//[statex-viewmodel](../../../index.md)/[com.kodetools.statex.viewmodel](../index.md)/[ViewModelStateContainer](index.md)

# ViewModelStateContainer

class [ViewModelStateContainer](index.md)&lt;[T](index.md)&gt; : [StateContainer](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/index.md)&lt;[T](index.md)&gt; 

A [StateContainer](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/index.md) that is directly associated with a [ViewModel](../-view-model/index.md) instance. It is used so that mutation operations only happen within [ViewModel](../-view-model/index.md) subclasses and read-only operations are exposed publicly through the [ViewModel](../-view-model/index.md) by default.

#### See also

| | |
|---|---|
| [ViewModel.viewModelStateContainerOf](../../../../statex-viewmodel/com.kodetools.statex.viewmodel/-view-model/view-model-state-container-of.md) | to create an instance of this class. |

## Properties

| Name | Summary |
|---|---|
| [current](index.md#2035780147%2FProperties%2F-552857832) | [common]<br>open override val [current](index.md#2035780147%2FProperties%2F-552857832): StateFlow&lt;[T](index.md)&gt; |
| [initial](index.md#805632712%2FProperties%2F-552857832) | [common]<br>open override val [initial](index.md#805632712%2FProperties%2F-552857832): StateFlow&lt;[T](index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [snapshot](index.md#1326224010%2FFunctions%2F-552857832) | [common]<br>open suspend override fun [snapshot](index.md#1326224010%2FFunctions%2F-552857832)(): [StateContainer.SnapshotStateModel](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt; |
