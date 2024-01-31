//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[StateContainer](index.md)/[initial](initial.md)

# initial

[common]\
abstract val [initial](initial.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt;

The initial [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) when this component was first created or when it was last reset. This value does not change when the [current](current.md) value changes. However, this value may change depending on the [StateContainer](index.md) implementation.

!Note That this is a Compose [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) value and will trigger recompositions of `@Composable` functions when used in the context of a `@Composable` function.

##  Example Usage

```kotlin
stateContainer.initial.value
```

#### See also

| | |
|---|---|
| [MutableStateContainer.reset](../-mutable-state-container/reset.md) | For a way of resetting this value. |
