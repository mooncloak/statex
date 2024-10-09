//[statex-persistence](../../index.md)/[com.mooncloak.kodetools.statex.persistence](index.md)/[persistableStateContainerOf](persistable-state-container-of.md)

# persistableStateContainerOf

[common]\

@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

inline fun &lt;[T](persistable-state-container-of.md)&gt; [persistableStateContainerOf](persistable-state-container-of.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), defaultValue: [T](persistable-state-container-of.md), serializersModule: SerializersModule = EmptySerializersModule(), serializer: KSerializer&lt;[T](persistable-state-container-of.md)&gt; = serializersModule.serializer(), policy: [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html)&lt;[T](persistable-state-container-of.md)&gt; = structuralEqualityPolicy(), storage: [PersistentStorage](-persistent-storage/index.md) = PersistentStorage.platformDefault()): [PersistableStateContainer](-persistable-state-container/index.md)&lt;[T](persistable-state-container-of.md)&gt;

Creates a [PersistableStateContainer](-persistable-state-container/index.md) with the provided values.

#### Parameters

common

| | |
|---|---|
| key | The key of the persisted value. |
| defaultValue | The default [StateContainer.initial](-persistable-state-container/index.md#-889809900%2FProperties%2F1961133779) value and the starting [StateContainer.current](-persistable-state-container/index.md#340337535%2FProperties%2F1961133779) value. Note that this value can change over time with a [MutableStateContainer](../../../statex-core/statex-core/com.mooncloak.kodetools.statex/-mutable-state-container/index.md), if the [MutableStateContainer.reset](-persistable-state-container/index.md#801414195%2FFunctions%2F1961133779) function is invoked. This value will be used if there is no current persisted value. |
| serializersModule | The SerializersModule used to obtain a default KSerializer for the value type if one is not provided. Defaults to EmptySerializersModule. |
| serializer | The KSerializer used to encode and decode the value from the persistent storage. |
| policy | The [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html) that is used to construct the underlying [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html) instances.<br>Example Usage<br>```kotlin val stateContainer = mutableStateContainerOf(true)<br>stateContainer.current.value // true stateContainer.initial.value // true stateContainer.changed.value // false<br>// Mutate the value stateContainer.change(value = false)<br>stateContainer.current.value // false stateContainer.initial.value // true stateContainer.changed.value // true ``` |
