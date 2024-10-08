//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[StateContainer](index.md)/[changed](changed.md)

# changed

[common]\
abstract val [changed](changed.md): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;

A [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) that determines whether the [current](current.md) value ever changed from the [initial](initial.md) value. Once the [current](current.md) value changes from the [initial](initial.md) value, this should always return a [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) with a value of `true`, even if the [current](current.md) value changed back to the same value of [initial](initial.md), until it is reset.

## Example Usage

```kotlin
stateContainer.changed.value
```

#### See also

| | |
|---|---|
| [MutableStateContainer.reset](../-mutable-state-container/reset.md) | To see how to reset a [MutableStateContainer](../-mutable-state-container/index.md) back to its initial state. |
