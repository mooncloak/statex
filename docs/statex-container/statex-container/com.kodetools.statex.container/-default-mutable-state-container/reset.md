//[statex-container](../../../index.md)/[com.kodetools.statex.container](../index.md)/[DefaultMutableStateContainer](index.md)/[reset](reset.md)

# reset

[common]\
open suspend override fun [reset](reset.md)(block: suspend (initial: [T](index.md)) -&gt; [T](index.md))

Resets the state to the value obtained from invoking the provided [block](reset.md) function.

!Note This is different from invoking the [update](update.md) function as it sets all the values back to their initial state, as if the initial value was the value obtained from invoking the provided [block](reset.md) function.

!Note All write operations for a [MutableStateContainer](../-mutable-state-container/index.md) are safe to access concurrently. This means that if another mutation is currently running while this function is invoked, then this function will suspend until that function has finished.

## Example Usage

```kotlin
// Reset back to the initial value when creating this StateContainer instance.
stateContainer.reset()

// Reset to a new initial value.
stateContainer.reset { newInitialValue }
```

#### Parameters

common

| | |
|---|---|
| block | The function that is invoked with the current initial state value and returns the initial state value to reset this [MutableStateContainer](../-mutable-state-container/index.md) to. Defaults to a block that returns the current initial state value. |
