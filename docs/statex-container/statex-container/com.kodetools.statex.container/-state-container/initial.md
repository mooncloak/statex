//[statex-container](../../../index.md)/[com.kodetools.statex.container](../index.md)/[StateContainer](index.md)/[initial](initial.md)

# initial

[common]\
abstract val [initial](initial.md): StateFlow&lt;[T](index.md)&gt;

The StateFlow of the initial value when this component was first created or when it was last reset. This value does not change when the [current](current.md) value changes via [MutableStateContainer.update](../-mutable-state-container/update.md) function calls. However, this value may change on invocations of the [MutableStateContainer.reset](../-mutable-state-container/reset.md) function.

## Example Usage

```kotlin
stateContainer.initial.value
```

#### See also

| | |
|---|---|
| [MutableStateContainer.reset](../-mutable-state-container/reset.md) | For a way of resetting this value. |
