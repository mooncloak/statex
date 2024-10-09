//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[DefaultMutableStateContainer](index.md)/[change](change.md)

# change

[common]\
open suspend override fun [change](change.md)(block: suspend (current: [T](index.md)) -&gt; [T](index.md))

Updates the [current](current.md) value to be the value obtained by invoking the provided [block](change.md) function. The [block](change.md) function is invoked within a lock, so subsequent calls to read the contained state values, such as [current](current.md), are guaranteed to return the same value, as no other mutations can occur until after the function returns and its result is emitted.

The provided [block](change.md) function is provided the [current](current.md) value for convenience. Though, explicitly accessing the [current](current.md) value will return the same value.

!Note All write operations for a [MutableStateContainer](../-mutable-state-container/index.md) are safe to access concurrently. This means that if another mutation is currently running while this function is invoked, then this function will suspend until that function has finished.

## Example Usage

```kotlin
stateContainer.change { current ->
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
