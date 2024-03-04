//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[StateContainer](index.md)

# StateContainer

@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

interface [StateContainer](index.md)&lt;[T](index.md)&gt;

A generic container around [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) values for a component. This encapsulates the [current](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) value, while retaining the [initial](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) value, and providing means to listen to state changes outside of Jetpack Compose components (Kotlinx Coroutines Flow).

Instead of using a [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) directly, one could use the [StateContainer](index.md) and [MutableStateContainer](../-mutable-state-container/index.md) components to encapsulate additional associated state, and handle mutations to the underlying value in a concurrency-safe manner.

!Note Implementations of this interface must guarantee conformance to the [Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html) annotation requirements.

#### See also

| | |
|---|---|
| [MutableStateContainer](../-mutable-state-container/index.md) | for a mutable version of this [StateContainer](index.md) interface. |
| [mutableStateContainerOf](../mutable-state-container-of.md) | To create a [MutableStateContainer](../-mutable-state-container/index.md) instance. |

#### Inheritors

| |
|---|
| [MutableStateContainer](../-mutable-state-container/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [changed](changed.md) | [common]<br>abstract val [changed](changed.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>A [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) that determines whether the [current](current.md) value ever changed from the [initial](initial.md) value. Once the [current](current.md) value changes from the [initial](initial.md) value, this should always return a [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) with a value of `true`, even if the [current](current.md) value changed back to the same value of [initial](initial.md), until it is reset. |
| [current](current.md) | [common]<br>abstract val [current](current.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt;<br>The current [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) for this component. This value can change over time, so subsequent calls to access this property can return different values. |
| [initial](initial.md) | [common]<br>abstract val [initial](initial.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt;<br>The initial [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) when this component was first created or when it was last reset. This value does not change when the [current](current.md) value changes. However, this value may change depending on the [StateContainer](index.md) implementation. |
| [stream](stream.md) | [common]<br>abstract val [stream](stream.md): StateFlow&lt;[T](index.md)&gt;<br>A Flow of changes that occur to the [current](current.md) value over time. Whenever the [current](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) value changes, that new value is emitted through this Flow. This is a StateFlow which means that it can retain the last emitted value which can be accessed like so: `container.stream.value`. |
