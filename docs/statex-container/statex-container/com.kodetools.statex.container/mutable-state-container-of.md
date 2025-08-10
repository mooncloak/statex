//[statex-container](../../index.md)/[com.kodetools.statex.container](index.md)/[mutableStateContainerOf](mutable-state-container-of.md)

# mutableStateContainerOf

[common]\
fun &lt;[T](mutable-state-container-of.md)&gt; [mutableStateContainerOf](mutable-state-container-of.md)(initialStateValue: [T](mutable-state-container-of.md), flowCoroutineScope: CoroutineScope, emitDispatcher: CoroutineDispatcher = Dispatchers.Main, flowDispatcher: CoroutineDispatcher = emitDispatcher, sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000), upstreamFlow: () -&gt; Flow&lt;[T](mutable-state-container-of.md)&gt; = { emptyFlow() }): [MutableStateContainer](-mutable-state-container/index.md)&lt;[T](mutable-state-container-of.md)&gt;

Creates a [MutableStateContainer](-mutable-state-container/index.md) with the provided [initialStateValue](mutable-state-container-of.md).

#### Parameters

common

| | |
|---|---|
| initialStateValue | The default [StateContainer.initial](-state-container/initial.md) value and the starting [StateContainer.current](-state-container/current.md) value. Note that this value can change over time with a [MutableStateContainer](-mutable-state-container/index.md), if the [MutableStateContainer.reset](-mutable-state-container/reset.md) function is invoked. |
| flowCoroutineScope | The CoroutineScope that is used to convert a Flow to a StateFlow for the [StateContainer.current](-state-container/current.md) property via the Flow.stateIn function. |
| emitDispatcher | The CoroutineDispatcher that is used to dispatch the changes to the states. Defaults to Dispatchers.Main. This could be updated to Dispatchers.Main on supported platforms. |
| flowDispatcher | The CoroutineDispatcher that is used to listen to the changes for the StateContainer.current property. This is typically used internally with a Flow.flowOn function call, before Flow.stateIn usage, when combining the [upstreamFlow](mutable-state-container-of.md) and internal MutableStateFlow usage for the [StateContainer.current](-state-container/current.md) value. |
| sharingStarted | The strategy that controls when sharing is started and stopped. This value is used to construct a StateFlow from the [upstreamFlow](mutable-state-container-of.md) function and the internal MutableStateFlow. |
| upstreamFlow | The function that returns a initial Flow of values for the [StateContainer.current](-state-container/current.md) property. This is typically used in tandem with an internal MutableStateFlow and the Flow.stateIn function.<br>Example Usage<br>```kotlin val stateContainer = mutableStateContainerOf(     initialStateValue = true,     flowCoroutineScope = coroutineScope )<br>stateContainer.current.value // true stateContainer.initial.value // true<br>// Mutate the value stateContainer.update(false)<br>stateContainer.current.value // false stateContainer.initial.value // true ``` |

[common]\
fun &lt;[T](mutable-state-container-of.md)&gt; [mutableStateContainerOf](mutable-state-container-of.md)(snapshot: [StateContainer.SnapshotStateModel](-state-container/-snapshot-state-model/index.md)&lt;[T](mutable-state-container-of.md)&gt;, flowCoroutineScope: CoroutineScope, emitDispatcher: CoroutineDispatcher = Dispatchers.Main, flowDispatcher: CoroutineDispatcher = emitDispatcher, sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000), upstreamFlow: () -&gt; Flow&lt;[T](mutable-state-container-of.md)&gt; = { emptyFlow() }): [MutableStateContainer](-mutable-state-container/index.md)&lt;[T](mutable-state-container-of.md)&gt;

Creates a [MutableStateContainer](-mutable-state-container/index.md) with the provided [snapshot](mutable-state-container-of.md).

#### Parameters

common

| | |
|---|---|
| snapshot | The [StateContainer.SnapshotStateModel](-state-container/-snapshot-state-model/index.md) value containing the values to use initially for the returned [MutableStateContainer](-mutable-state-container/index.md) instance. |
| flowCoroutineScope | The CoroutineScope that is used to convert a Flow to a StateFlow for the [StateContainer.current](-state-container/current.md) property via the Flow.stateIn function. |
| emitDispatcher | The CoroutineDispatcher that is used to dispatch the changes to the states. Defaults to Dispatchers.Main. This could be updated to Dispatchers.Main on supported platforms. |
| flowDispatcher | The CoroutineDispatcher that is used to listen to the changes for the StateContainer.current property. This is typically used internally with a Flow.flowOn function call, before Flow.stateIn usage, when combining the [upstreamFlow](mutable-state-container-of.md) and internal MutableStateFlow usage for the [StateContainer.current](-state-container/current.md) value. |
| sharingStarted | The strategy that controls when sharing is started and stopped. This value is used to construct a StateFlow from the [upstreamFlow](mutable-state-container-of.md) function and the internal MutableStateFlow. |
| upstreamFlow | The function that returns a initial Flow of values for the [StateContainer.current](-state-container/current.md) property. This is typically used in tandem with an internal MutableStateFlow and the Flow.stateIn function.<br>Example Usage<br>```kotlin val initialStateContainer = mutableStateContainer(     initialStateValue = true,     flowCoroutineScope = coroutineScope ) val snapshot = initialStateContainer.snapshot() val stateContainer = mutableStateContainerOf(     snapshot = snapshot,     flowCoroutineScope = coroutineScope )<br>stateContainer.current.value // true stateContainer.initial.value // true<br>// Mutate the value stateContainer.update(false)<br>stateContainer.current.value // false stateContainer.initial.value // true ``` |
