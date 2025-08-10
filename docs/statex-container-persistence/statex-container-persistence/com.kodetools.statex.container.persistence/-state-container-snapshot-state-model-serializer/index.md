//[statex-container-persistence](../../../index.md)/[com.kodetools.statex.container.persistence](../index.md)/[StateContainerSnapshotStateModelSerializer](index.md)

# StateContainerSnapshotStateModelSerializer

[common]\
class [StateContainerSnapshotStateModelSerializer](index.md)&lt;[T](index.md)&gt;(valueSerializer: KSerializer&lt;[T](index.md)&gt;) : KSerializer&lt;[StateContainer.SnapshotStateModel](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt;&gt;

## Constructors

| | |
|---|---|
| [StateContainerSnapshotStateModelSerializer](-state-container-snapshot-state-model-serializer.md) | [common]<br>constructor(valueSerializer: KSerializer&lt;[T](index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [descriptor](descriptor.md) | [common]<br>open override val [descriptor](descriptor.md): SerialDescriptor |

## Functions

| Name | Summary |
|---|---|
| [deserialize](deserialize.md) | [common]<br>open override fun [deserialize](deserialize.md)(decoder: Decoder): [StateContainer.SnapshotStateModel](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt; |
| [serialize](serialize.md) | [common]<br>open override fun [serialize](serialize.md)(encoder: Encoder, value: [StateContainer.SnapshotStateModel](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/-snapshot-state-model/index.md)&lt;[T](index.md)&gt;) |
