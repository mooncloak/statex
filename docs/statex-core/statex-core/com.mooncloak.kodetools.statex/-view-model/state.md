//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[ViewModel](index.md)/[state](state.md)

# state

[common]\
val [state](state.md): [StateContainer](../-state-container/index.md)&lt;[T](index.md)&gt;

Provides access to the read-only [StateContainer](../-state-container/index.md) values. [ViewModel](index.md) implementations can mutate the wrapped state by emitting new state values via the protected [emit](../../../../statex-core/com.mooncloak.kodetools.statex/-view-model/emit.md) and [reset](../../../../statex-core/com.mooncloak.kodetools.statex/-view-model/reset.md) functions.

## Example Usage:

```kotlin
@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    modifier: Modifier = Modifier
) {
    // Retrieve and use the compose State<T> value
    val currentState by viewModel.state.current

    AnimatedVisibility(
        visible = currentState.isLoading // Example usage of the underlying state value
    ) { ... }
}
```

#### See also

| |
|---|
| [StateContainer](../-state-container/index.md) |
