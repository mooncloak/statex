//[statex-core](../../index.md)/[com.mooncloak.kodetools.statex](index.md)/[update](update.md)

# update

[common]\
suspend fun &lt;[T](update.md)&gt; [MutableStateContainer](-mutable-state-container/index.md)&lt;[T](update.md)&gt;.[update](update.md)(value: [T](update.md))

Updates the [StateContainer.current](-state-container/current.md) value to be the provided [value](update.md). This is a convenience function that delegates to the [MutableStateContainer.update](-mutable-state-container/update.md) by providing a higher-order function that simply returns the provided [value](update.md).

!Note All write operations for a [MutableStateContainer](-mutable-state-container/index.md) are safe to access concurrently. This means that if another mutation is currently running while this function is invoked, then this function will suspend until that function has finished.

## Example Usage

```kotlin
stateContainer.update(value = newValue)
```

#### Parameters

common

| | |
|---|---|
| value | The value to change the [StateContainer.current](-state-container/current.md) value to. |
