//[statex-container-persistence](../../index.md)/[com.kodetools.statex.container.persistence](index.md)/[persistableStateContainerOf](persistable-state-container-of.md)

# persistableStateContainerOf

[common]\

@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

inline fun &lt;[T](persistable-state-container-of.md)&gt; [persistableStateContainerOf](persistable-state-container-of.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), defaultValue: [T](persistable-state-container-of.md), serializersModule: SerializersModule = EmptySerializersModule(), serializer: KSerializer&lt;[T](persistable-state-container-of.md)&gt; = serializersModule.serializer(), policy: [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html)&lt;[T](persistable-state-container-of.md)&gt; = structuralEqualityPolicy(), storage: [PersistentStorage](-persistent-storage/index.md) = PersistentStorage.platformDefault(), dispatcher: MainCoroutineDispatcher = Dispatchers.Main): [PersistableStateContainer](-persistable-state-container/index.md)&lt;[T](persistable-state-container-of.md)&gt;

Creates a [PersistableStateContainer](-persistable-state-container/index.md) with the provided values.

#### Parameters

common

| | |
|---|---|
| key | The key of the persisted value. |
| defaultValue | The default StateContainer.initial value and the starting StateContainer.current value. Note that this value can change over time with a [MutableStateContainer](../../../statex-container/statex-container/com.kodetools.statex.container/-mutable-state-container/index.md), if the [MutableStateContainer.reset](-persistable-state-container/index.md#-1811025550%2FFunctions%2F1919035847) function is invoked. This value will be used if there is no current persisted value. |
| serializersModule | The SerializersModule used to obtain a default KSerializer for the value type if one is not provided. Defaults to EmptySerializersModule. |
| serializer | The KSerializer used to encode and decode the value from the persistent storage. |
| policy | The [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html) that is used to construct the underlying [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html) instances. |
| dispatcher | The MainCoroutineDispatcher that is used to dispatch the changes to the state. Defaults to Dispatchers.Main. This could be updated to Dispatchers.Main on supported platforms.<br>Example Usage<br>```kotlin val stateContainer = mutableStateContainerOf(true)<br>stateContainer.current.value // true stateContainer.initial.value // true stateContainer.changed.value // false<br>// Mutate the value stateContainer.change(value = false)<br>stateContainer.current.value // false stateContainer.initial.value // true stateContainer.changed.value // true ``` |
