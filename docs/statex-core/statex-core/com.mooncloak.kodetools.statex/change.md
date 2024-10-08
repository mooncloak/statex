//[statex-core](../../index.md)/[com.mooncloak.kodetools.statex](index.md)/[change](change.md)

# change

[common]\
suspend fun &lt;[T](change.md)&gt; [MutableStateContainer](-mutable-state-container/index.md)&lt;[T](change.md)&gt;.[change](change.md)(value: [T](change.md))

Updates the [StateContainer.current](-state-container/current.md) value to be the provided [value](change.md). This is a convenience function that delegates to the [MutableStateContainer.change](-mutable-state-container/change.md) by providing a higher-order function that simply returns the provided [value](change.md).

!Note All write operations for a [MutableStateContainer](-mutable-state-container/index.md) are safe to access concurrently. This means that if another mutation is currently running while this function is invoked, then this function will suspend until that function has finished.

## Example Usage

```kotlin
stateContainer.change(value = newValue)
```

#### Parameters

common

| | |
|---|---|
| value | The value to change the [StateContainer.current](-state-container/current.md) value to. |
