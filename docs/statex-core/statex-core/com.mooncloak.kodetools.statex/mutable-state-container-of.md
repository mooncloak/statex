//[statex-core](../../index.md)/[com.mooncloak.kodetools.statex](index.md)/[mutableStateContainerOf](mutable-state-container-of.md)

# mutableStateContainerOf

[common]\

@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

fun &lt;[T](mutable-state-container-of.md)&gt; [mutableStateContainerOf](mutable-state-container-of.md)(initialValue: [T](mutable-state-container-of.md)): [MutableStateContainer](-mutable-state-container/index.md)&lt;[T](mutable-state-container-of.md)&gt;

Creates a [MutableStateContainer](-mutable-state-container/index.md) with the provided [initialValue](mutable-state-container-of.md).

##  Example Usage

```kotlin
val stateContainer = mutableStateContainerOf(true)

stateContainer.current.value // true
stateContainer.initial.value // true
stateContainer.changed.value // false

// Mutate the value
stateContainer.change(value = false)

stateContainer.current.value // false
stateContainer.initial.value // true
stateContainer.changed.value // true
```
