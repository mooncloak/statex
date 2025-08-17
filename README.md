# Statex

![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/mooncloak/statex)

A modern, multiplatform state management toolkit for Kotlin and Compose applications.

## Overview 🎯

Statex provides a set of powerful, yet easy-to-use tools for managing state in your Kotlin multiplatform applications.
It consists of several modules:

- **statex-viewmodel**: A ViewModel implementation with built-in state management
- **statex-container**: A lightweight, flexible state container system
- **statex-container-persistence**: Persistence extensions for state containers

## Features ✨

- 🎯 **Multiplatform Support**: Works across Android, iOS, Desktop, and Web
- 🔄 **Unidirectional Data Flow**: Clean and predictable state management
- 📦 **Modular Design**: Use only what you need
- 🛡️ **Type-safe**: Leverages Kotlin's type system for robust state handling
- 🎨 **Compose Integration**: Seamless integration with Jetpack Compose

## Getting Started 🏁

### Add the Repository

```kotlin
repositories { maven("https://repo.repsy.io/mvn/mooncloak/public") }
``` 

### Choose Your Dependencies

```kotlin 
implementation("com.kodetools.statex:statex-viewmodel:VERSION")
implementation("com.kodetools.statex:statex-container:VERSION")
implementation("com.kodetools.statex:statex-container-persistence:VERSION")
``` 

## Usage Examples 💡

### ViewModel Example

```kotlin 
data class CounterState(val count: Int = 0)

class CounterViewModel : ViewModel(initialStateValue = CounterState()) {
    fun increment() {
        viewModelScope.launch {
            uiState.mutable.update { current ->
                current.copy(count = current.count + 1)
            }
        }
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    val state by viewModel.uiState.current.collectAsState()
    Button(onClick = { viewModel.increment() }) {
        Text("Count: ${state.count}")
    }
}
```

#### State Models

State models are immutable data models that represent your UI state. They can be sealed or data classes.

```kotlin
sealed class HomeStateModel {
    data object Loading : HomeStateModel()
    data class Success(val items: List<HomeItem>) : HomeStateModel()
    data class Error(val message: String) : HomeStateModel()
}

data class FeedStateModel(
    val items: List<FeedItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
```

#### ViewModels

ViewModels are classes that manage and hold state and perform application logic. They extend the `ViewModel` class and
have an initial uiState value.

```kotlin
class HomeViewModel : ViewModel(initialStateValue = HomeStateModel.Loading) { ... }
```

##### StateContainer

Every ViewModel has a `ViewModel.uiState` property which is a `StateContainer` around the state models that can be
emitted from the ViewModel, starting with the `initialStateValue` provided to the `ViewModel` constructor. Every
`StateContainer` exposes `current` and `initial` `StateFlow` values which can be observed in a `@Composable` function:

```kotlin
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.uiState.current.collectAsState()
    when (state) {
        HomeStateModel.Loading -> LoadingLayout(state)
        is HomeStateModel.Success -> SuccessLayout(state)
        is HomeStateModel.Error -> ErrorLayout(state)
    }
}
```

##### Updating State

The `ViewModel.uiState` is a `ViewModelStateContainer` implementation of a `StateContainer`, which exposes its
`MutableStateContainer` version via a protected scoped `mutable` function. This allows ViewModel subclasses to mutate
the state without letting external components be able to mutate the state.

```kotlin
class HomeViewModel : ViewModel(initialStateValue = HomeStateModel.Loading) {
    fun load() {
        viewModelScope.launch {
            try {
                val items = loadItems()

                uiState.mutable.update(HomeStateModel.Success(items = items))
            } catch (e: Exception) {
                uiState.mutable.update(HomeStateModel.Error(message = e.message))
            }
        }
    }
}
```

There is also a `withMutable` convenience function:

```kotlin
fun load() {
    viewModelScope.launch {
        uiState.withMutable {
            this.update(HomeStateModel.Loading)
            
            this.update { current -> current }
        }
    }
}
```

##### Upstream State

Sometimes it is required to start with existing `Flows`, and this can be done by implementing the
`ViewModel.uiStateUpstreamFlow` function. The first value emitted will be the `initialStateValue`, followed by the
upstream flow values.

```kotlin
class HomeViewModel(
    private val repository: ItemRepository
) : ViewModel(initialStateValue = HomeStateModel.Loading) {
    override fun uiStateUpstreamFlow(): Flow<HomeStateModel> = flow {
        val items = repository.getInitialItems()
        emit(HomeStateModel.Success(items))
    }
}
```

##### Creating State

The `viewModelStateContainerOf` function can be used within a `ViewModel` to create another `StateContainer`.

```kotlin
class HomeViewModel : ViewModel(initialStateValue = HomeStateModel.Loading) {
    val anotherState: ViewModelStateContainer<String> = viewModelStateContainerOf(initialStateValue = "Hello")
}
```

### State Container Usage

```kotlin 
val stateContainer = mutableStateContainerOf(initialStateValue = false)
// Access current state 
val currentValue = stateContainer.current.value
// Update state 
stateContainer.update { !it }
``` 

## Documentation 📚

Detailed documentation is available in the [docs](docs/) folder:

- [Getting Started Guide](docs/index.md)
- [ViewModel Documentation](docs/viewmodel.md)
- [State Container Guide](docs/state-container.md)
- [API Reference](docs/api-reference.md)

## Contributing 🤝

We welcome contributions! Please check out our:

- [Code of Conduct](CODE_OF_CONDUCT.md)
- [Coding Conventions](CODING_CONVENTIONS.md)
- [Security Policy](SECURITY.md)

## License ⚖️

```
Copyright 2024 mooncloak

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
