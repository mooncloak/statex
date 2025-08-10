//[statex-container](../../index.md)/[com.kodetools.statex.container](index.md)/[reset](reset.md)

# reset

[common]\
suspend fun &lt;[T](reset.md)&gt; [MutableStateContainer](-mutable-state-container/index.md)&lt;[T](reset.md)&gt;.[reset](reset.md)(initialStateValue: [T](reset.md))

Resets the state to the provided [initialStateValue](reset.md). This provides a way to override what the initial value was by providing an [initialStateValue](reset.md) as a parameter.

!Note This is different from invoking the [update](update.md) function as it sets all the values back to their initial state, as if the initial value was the provided [initialStateValue](reset.md).

!Note All write operations for a [MutableStateContainer](-mutable-state-container/index.md) are safe to access concurrently. This means that if another mutation is currently running while this function is invoked, then this function will suspend until that function has finished.

## Example Usage

```kotlin
// Reset back to the initial value when creating this StateContainer instance.
stateContainer.reset()

// Reset to a new initial value.
stateContainer.reset(newInitialValue)
```

#### Parameters

common

| | |
|---|---|
| initialStateValue | The value to set as the initial value. Defaults to [StateContainer.initial](-state-container/initial.md). |
