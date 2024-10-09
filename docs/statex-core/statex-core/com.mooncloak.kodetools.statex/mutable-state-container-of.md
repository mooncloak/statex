//[statex-core](../../index.md)/[com.mooncloak.kodetools.statex](index.md)/[mutableStateContainerOf](mutable-state-container-of.md)

# mutableStateContainerOf

[common]\

@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

fun &lt;[T](mutable-state-container-of.md)&gt; [mutableStateContainerOf](mutable-state-container-of.md)(initialValue: [T](mutable-state-container-of.md), policy: [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html)&lt;[T](mutable-state-container-of.md)&gt; = structuralEqualityPolicy()): [MutableStateContainer](-mutable-state-container/index.md)&lt;[T](mutable-state-container-of.md)&gt;

Creates a [MutableStateContainer](-mutable-state-container/index.md) with the provided [initialValue](mutable-state-container-of.md).

#### Parameters

common

| | |
|---|---|
| initialValue | The default [StateContainer.initial](-state-container/initial.md) value and the starting [StateContainer.current](-state-container/current.md) value. Note that this value can change over time with a [MutableStateContainer](-mutable-state-container/index.md), if the [MutableStateContainer.reset](-mutable-state-container/reset.md) function is invoked. |
| policy | The [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html) that is used to construct the underlying [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html) instances.<br>Example Usage<br>```kotlin val stateContainer = mutableStateContainerOf(true)<br>stateContainer.current.value // true stateContainer.initial.value // true stateContainer.changed.value // false<br>// Mutate the value stateContainer.change(value = false)<br>stateContainer.current.value // false stateContainer.initial.value // true stateContainer.changed.value // true ``` |

[common]\

@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

fun &lt;[T](mutable-state-container-of.md)&gt; [mutableStateContainerOf](mutable-state-container-of.md)(snapshot: [StateContainer.SnapshotStateModel](-state-container/-snapshot-state-model/index.md)&lt;[T](mutable-state-container-of.md)&gt;, policy: [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html)&lt;[T](mutable-state-container-of.md)&gt; = structuralEqualityPolicy()): [MutableStateContainer](-mutable-state-container/index.md)&lt;[T](mutable-state-container-of.md)&gt;

Creates a [MutableStateContainer](-mutable-state-container/index.md) with the provided [snapshot](mutable-state-container-of.md).

#### Parameters

common

| | |
|---|---|
| snapshot | The [StateContainer.SnapshotStateModel](-state-container/-snapshot-state-model/index.md) value containing the values to use initially for the returned [MutableStateContainer](-mutable-state-container/index.md) instance. |
| policy | The [SnapshotMutationPolicy](https://developer.android.com/reference/kotlin/androidx/compose/runtime/SnapshotMutationPolicy.html) that is used to construct the underlying [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html) instances.<br>Example Usage<br>```kotlin val initialStateContainer = mutableStateContainer(true) val snapshot = initialStateContainer.snapshot() val stateContainer = mutableStateContainerOf(snapshot)<br>stateContainer.current.value // true stateContainer.initial.value // true stateContainer.changed.value // false<br>// Mutate the value stateContainer.change(value = false)<br>stateContainer.current.value // false stateContainer.initial.value // true stateContainer.changed.value // true ``` |
