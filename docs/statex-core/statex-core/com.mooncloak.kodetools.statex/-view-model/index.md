//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[ViewModel](index.md)

# ViewModel

abstract class [ViewModel](index.md)&lt;[T](index.md)&gt;(initialStateValue: [T](index.md)) : [PlatformViewModel](../-platform-view-model/index.md)

A design pattern level component that encapsulates state management and application logic for a user interface component or concept, such as a screen within an application. A [ViewModel](index.md) follows the uni-directional data flow (UDF) approach recommended by the Jetpack Compose documentation.

A [ViewModel](index.md) exposes a single [StateContainer](../-state-container/index.md) of the wrapped [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) values via the [state](state.md) property. This [state](state.md) property provides a stream of state changes that occur as a result of the application logic within the [ViewModel](index.md) function, and can be subscribed to inside or outside the context of a `@Composable` function.

Functions within a [ViewModel](index.md) should handle performing application logic, coordinating and invoking business logic, mapping to the appropriate models, and emitting the updated state values as a result of those changes. Publicly exposed functions will be invoked from the user interface components as a result of some action (ex: user interaction). It is recommended to keep these functions as non-suspending functions, as a [ViewModel](index.md) contains its own lifecycle and has a [coroutineScope](../../../../statex-core/com.mooncloak.kodetools.statex/-view-model/coroutine-scope.md) that can be used to launch coroutines internally. This removes the need for the call-site to have to wrap the function invocations in a coroutine scope themselves.

!Note A [ViewModel](index.md) has its own lifecycle and must be bound to the user interface component (ex: `@Composable` function, View, etc.) for it to work correctly.

##  Example Usage

```kotlin
class FeedViewModel : ViewModel(initialStateValue = FeedStateModel()) {

   fun load() {
       emit(value = state.current.value.copy(isLoading = true))

       val items = withContext(Dispatchers.IO) {
           feedApi.load()
       }

       emit(value = state.current.value.copy(isLoading = false, items = items))
   }
}
```

#### Parameters

common

| | |
|---|---|
| initialStateValue | The initial value to provide to the [mutableStateContainer](../../../../statex-core/com.mooncloak.kodetools.statex/-view-model/mutable-state-container.md) function when constructing the [StateContainer](../-state-container/index.md) instance for the [state](state.md) property. |

## Constructors

| | |
|---|---|
| [ViewModel](-view-model.md) | [common]<br>constructor(initialStateValue: [T](index.md)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | [common]<br>fun [bind](bind.md)() |
| [unbind](unbind.md) | [common]<br>fun [unbind](unbind.md)() |

## Properties

| Name | Summary |
|---|---|
| [isBound](is-bound.md) | [common]<br>var [isBound](is-bound.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [state](state.md) | [common]<br>val [state](state.md): [StateContainer](../-state-container/index.md)&lt;[T](index.md)&gt;<br>Provides access to the read-only [StateContainer](../-state-container/index.md) values. [ViewModel](index.md) implementations can mutate the wrapped state by emitting new state values via the protected [emit](../../../../statex-core/com.mooncloak.kodetools.statex/-view-model/emit.md) and [reset](../../../../statex-core/com.mooncloak.kodetools.statex/-view-model/reset.md) functions. |
