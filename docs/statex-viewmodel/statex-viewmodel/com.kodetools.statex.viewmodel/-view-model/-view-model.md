//[statex-viewmodel](../../../index.md)/[com.kodetools.statex.viewmodel](../index.md)/[ViewModel](index.md)/[ViewModel](-view-model.md)

# ViewModel

[common]\
constructor(initialStateValue: [T](index.md), emitDispatcher: CoroutineDispatcher = Dispatchers.Main, flowDispatcher: CoroutineDispatcher = emitDispatcher, sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000))

#### Parameters

common

| | |
|---|---|
| initialStateValue | The initial state model value for the [ViewModel.uiState](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/index.md). |
| emitDispatcher | The CoroutineDispatcher used to emit state models via the [MutableStateContainer.update](../../../../statex-container/statex-container/com.kodetools.statex.container/-mutable-state-container/update.md) function invocations. |
| flowDispatcher | The CoroutineDispatcher that is used to listen to the changes for the com.kodetools.statex.container.StateContainer.current property. This is typically used internally with a Flow.flowOn function call, before Flow.stateIn usage. |
| sharingStarted | The strategy that controls when sharing is started and stopped. This value is used to construct a StateFlow from the [ViewModel.uiStateUpstreamFlow](../../../../statex-viewmodel/com.kodetools.statex.viewmodel/-view-model/ui-state-upstream-flow.md) function and the internal MutableStateFlow. |
