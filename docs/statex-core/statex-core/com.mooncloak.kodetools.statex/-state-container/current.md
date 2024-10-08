//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[StateContainer](index.md)/[current](current.md)

# current

[common]\
abstract val [current](current.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[T](index.md)&gt;

The current [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) for this component. This value can change over time, so subsequent calls to access this property can return different values.

!Note That this is a Compose [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) value and will trigger recompositions of `@Composable` functions when used in the context of a `@Composable` function.

## Example Usage

```kotlin
stateContainer.current.value
```

#### See also

| | |
|---|---|
| [MutableStateContainer.change](../-mutable-state-container/change.md) | For a way of changing this value. |
| [MutableStateContainer.reset](../-mutable-state-container/reset.md) | For a way of resetting this value. |
