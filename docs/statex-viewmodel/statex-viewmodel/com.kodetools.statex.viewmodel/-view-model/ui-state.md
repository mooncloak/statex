//[statex-viewmodel](../../../index.md)/[com.kodetools.statex.viewmodel](../index.md)/[ViewModel](index.md)/[uiState](ui-state.md)

# uiState

[common]\
val [uiState](ui-state.md): [ViewModelStateContainer](../-view-model-state-container/index.md)&lt;[T](index.md)&gt;

Represents the state container for managing UI state within the ViewModel.

This property defines an instance of [ViewModelStateContainer](../-view-model-state-container/index.md), which is responsible for holding and managing the state of type [T](index.md) throughout the lifecycle of the ViewModel. It utilizes a combination of declarative state management and reactive flows, allowing state changes to be observed and updated efficiently.

This container is tied to the [viewModelScope](../../../../statex-viewmodel/com.kodetools.statex.viewmodel/-view-model/view-model-scope.md) of the ViewModel, ensuring proper lifecycle management. The ability to mutate the state is restricted to [ViewModel](index.md) subclasses, while external consumers can only observe the state.
