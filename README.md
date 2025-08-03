# Statex

![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/mooncloak/statex)

A modern, multiplatform state management toolkit for Kotlin and Compose applications.

## Overview 🎯

Statex provides a set of powerful, yet easy-to-use tools for managing state in your Kotlin multiplatform applications. It consists of several modules:

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

### Basic ViewModel Usage

```kotlin 
class CounterViewModel : ViewModel(initialStateValue = CounterState()) { 
    fun increment() { 
        viewModelScope.launch { 
            emit { current -> 
                current.copy(count = current.count + 1) 
            } 
        } 
    } 
}

@Composable 
fun CounterScreen(viewModel: CounterViewModel) { 
    val state by viewModel.state.collectAsState()
    Button(onClick = { viewModel.increment() }) {
        Text("Count: ${state.count}")
    }
}
``` 

### State Container Usage

```kotlin 
val stateContainer = mutableStateContainerOf(snapshot = StateContainer.SnapshotStateModel(initial = true, current = false))
// Access current state 
val currentValue = stateContainer.current.value 
val hasChanged = stateContainer.changed.value
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
