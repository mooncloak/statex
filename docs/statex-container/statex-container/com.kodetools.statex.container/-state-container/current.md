//[statex-container](../../../index.md)/[com.kodetools.statex.container](../index.md)/[StateContainer](index.md)/[current](current.md)

# current

[common]\
abstract val [current](current.md): StateFlow&lt;[T](index.md)&gt;

The StateFlow of the current value of this [StateContainer](index.md).

## Example Usage

```kotlin
stateContainer.current.value
```

#### See also

| | |
|---|---|
| [MutableStateContainer.update](../-mutable-state-container/update.md) | For a way of changing this value. |
| [MutableStateContainer.reset](../-mutable-state-container/reset.md) | For a way of resetting this value back to the initial value. |
