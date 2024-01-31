//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[MutableStateContainer](index.md)/[reset](reset.md)

# reset

[common]\
abstract suspend fun [reset](reset.md)(initialValue: [T](index.md) = this.initial.value)

Resets the state to the provided [initialValue](reset.md). This provides a way to override what the initial value was by providing an [initialValue](reset.md) as a parameter.

!Note This is different from invoking the [change](change.md) function as it sets all the values back to their initial state, as if the initial value was the provided [initialValue](reset.md).

!Note All write operations for a [MutableStateContainer](index.md) are safe to access concurrently. This means that if another mutation is currently running while this function is invoked, then this function will suspend until that function has finished.

##  Example Usage

```kotlin
// Reset back to the initial value when creating this StateContainer instance.
stateContainer.reset()

// Reset to a new initial value.
stateContainer.reset(initialValue = newInitialValue)
```

#### Parameters

common

| | |
|---|---|
| initialValue | The value to set as the initial value. Defaults to [StateContainer.initial](../-state-container/initial.md). |
