//[statex-viewmodel](../../../index.md)/[com.kodetools.statex.viewmodel](../index.md)/[ViewModel](index.md)

# ViewModel

abstract class [ViewModel](index.md)&lt;[T](index.md) : [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)&gt;(initialStateValue: [T](index.md), emitDispatcher: CoroutineDispatcher = Dispatchers.Main, flowDispatcher: CoroutineDispatcher = emitDispatcher, sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000)) : [PlatformViewModel](../-platform-view-model/index.md)

A design pattern level component that encapsulates state management and application logic for a user interface component or concept, such as a &quot;screen&quot; within an application. A [ViewModel](index.md) follows the uni-directional data flow (UDF) approach recommended by the Jetpack Compose documentation.

A [ViewModel](index.md) exposes a single StateFlow of the wrapped state model values via the [uiState](ui-state.md) property. This [uiState](ui-state.md) property provides a stream of state changes that occur as a result of the application logic within the [ViewModel](index.md) function, and can be subscribed to inside or outside the context of a `@Composable` function.

Functions within a [ViewModel](index.md) should handle performing application logic, coordinating and invoking business logic, mapping to the appropriate models, and emitting the updated state values as a result of those changes. Publicly exposed functions will be invoked from the user interface components as a result of some action (ex: user interaction). It is recommended to keep these functions as non-suspending functions, as a [ViewModel](index.md) contains its own lifecycle and has a CoroutineScope that can be used to launch coroutines internally via the [viewModelScope](../../../../statex-viewmodel/com.kodetools.statex.viewmodel/-view-model/view-model-scope.md) property. This removes the need for the call-site to have to wrap the function invocations in a coroutine scope themselves.

!Note When using Jetpack Compose or Compose Multiplatform, implementations of this interface must guarantee conformance to the Stable annotation requirements.

## Example Usage

```kotlin
class FeedViewModel : ViewModel(initialStateValue = FeedStateModel()) {

   fun load() {
       viewModelScope.launch {
           uiState.mutable.update { current -> current.copy(isLoading = true) }

           val items = withContext(Dispatchers.IO) {
               feedApi.load()
           }

           uiState.mutable.update { current -> current.copy(isLoading = false, items = items) }
       }
   }
}
```

#### Parameters

common

| | |
|---|---|
| initialStateValue | The initial state model value for the [ViewModel.uiState](../../../../statex-container/statex-container/com.kodetools.statex.container/-state-container/index.md). |
| emitDispatcher | The CoroutineDispatcher used to emit state models via the [MutableStateContainer.update](../../../../statex-container/statex-container/com.kodetools.statex.container/-mutable-state-container/update.md) function invocations. |
| flowDispatcher | The CoroutineDispatcher that is used to listen to the changes for the com.kodetools.statex.container.StateContainer.current property. This is typically used internally with a Flow.flowOn function call, before Flow.stateIn usage. |
| sharingStarted | The strategy that controls when sharing is started and stopped. This value is used to construct a StateFlow from the [ViewModel.uiStateUpstreamFlow](../../../../statex-viewmodel/com.kodetools.statex.viewmodel/-view-model/ui-state-upstream-flow.md) function and the internal MutableStateFlow. |

## Constructors

| | |
|---|---|
| [ViewModel](-view-model.md) | [common]<br>constructor(initialStateValue: [T](index.md), emitDispatcher: CoroutineDispatcher = Dispatchers.Main, flowDispatcher: CoroutineDispatcher = emitDispatcher, sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5_000)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [uiState](ui-state.md) | [common]<br>val [uiState](ui-state.md): [ViewModelStateContainer](../-view-model-state-container/index.md)&lt;[T](index.md)&gt;<br>Represents the state container for managing UI state within the ViewModel. |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-platform-view-model/add-closeable.md) | [common]<br>expect fun [addCloseable](../-platform-view-model/add-closeable.md)(closeable: [AutoCloseable](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-auto-closeable/index.html))<br>expect fun [addCloseable](../-platform-view-model/add-closeable.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-auto-closeable/index.html)) |
| [getCloseable](../-platform-view-model/get-closeable.md) | [common]<br>expect fun &lt;[T](../-platform-view-model/get-closeable.md) : [AutoCloseable](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-auto-closeable/index.html)&gt; [getCloseable](../-platform-view-model/get-closeable.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [T](../-platform-view-model/get-closeable.md)? |
