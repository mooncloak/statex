//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[StateContainer](index.md)/[stream](stream.md)

# stream

[common]\
abstract val [stream](stream.md): StateFlow&lt;[T](index.md)&gt;

A Flow of changes that occur to the [current](current.md) value over time. Whenever the [current](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html) value changes, that new value is emitted through this Flow. This is a StateFlow which means that it can retain the last emitted value which can be accessed like so: `container.stream.value`.

!Note This is a &quot;hot&quot; flow, so changes can be emitted to it even when there are no subscribers. Since this is a StateFlow instance, it can be shared by multiple subscribers.

##  Example Usage

```kotlin
stateContainer.stream.onEach { ... }
                     .launchIn(coroutineScope)
```
