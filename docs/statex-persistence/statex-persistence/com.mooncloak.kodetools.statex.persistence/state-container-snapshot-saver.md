//[statex-persistence](../../index.md)/[com.mooncloak.kodetools.statex.persistence](index.md)/[stateContainerSnapshotSaver](state-container-snapshot-saver.md)

# stateContainerSnapshotSaver

[common]\
fun &lt;[T](state-container-snapshot-saver.md)&gt; [stateContainerSnapshotSaver](state-container-snapshot-saver.md)(format: StringFormat, serializer: KSerializer&lt;[T](state-container-snapshot-saver.md)&gt;): [StringFormatSaver](-string-format-saver/index.md)&lt;[StateContainer.SnapshotStateModel](../../../statex-core/statex-core/com.mooncloak.kodetools.statex/-state-container/-snapshot-state-model/index.md)&lt;[T](state-container-snapshot-saver.md)&gt;&gt;

fun &lt;[T](state-container-snapshot-saver.md)&gt; [stateContainerSnapshotSaver](state-container-snapshot-saver.md)(format: BinaryFormat, serializer: KSerializer&lt;[T](state-container-snapshot-saver.md)&gt;): [BinaryFormatByteArraySaver](-binary-format-byte-array-saver/index.md)&lt;[StateContainer.SnapshotStateModel](../../../statex-core/statex-core/com.mooncloak.kodetools.statex/-state-container/-snapshot-state-model/index.md)&lt;[T](state-container-snapshot-saver.md)&gt;&gt;
