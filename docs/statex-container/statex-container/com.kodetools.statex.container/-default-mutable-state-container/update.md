//[statex-container](../../../index.md)/[com.kodetools.statex.container](../index.md)/[DefaultMutableStateContainer](index.md)/[update](update.md)

# update

[common]\
open suspend override fun [update](update.md)(block: suspend (current: [T](index.md)) -&gt; [T](index.md))

Updates the [current](current.md) value to be the value obtained by invoking the provided [block](update.md) function. The [block](update.md) function is invoked within a lock, so subsequent calls to read the contained state values, such as [current](current.md), are guaranteed to return the same value, as no other mutations can occur until after the function returns and its result is emitted.

The provided [block](update.md) function is provided the [current](current.md) value for convenience. Though, explicitly accessing the [current](current.md) value will return the same value.

!Note All write operations for a [MutableStateContainer](../-mutable-state-container/index.md) are safe to access concurrently. This means that if another mutation is currently running while this function is invoked, then this function will suspend until that function has finished.

## Example Usage

```kotlin
stateContainer.update { current ->
    // Perform some logic

    // Return an updated value from this function
    current.copy(...)
}
```

#### Parameters

common

| | |
|---|---|
| block | The function that will be invoked to obtain the new [current](current.md) value. |
