//[statex-viewmodel](../../../index.md)/[com.kodetools.statex.viewmodel](../index.md)/[ViewModelStore](index.md)

# ViewModelStore

[common]\
expect open class [ViewModelStore](index.md)

Class to store `ViewModel`s.

Instances of `ViewModelStore` must be retained through configuration changes. If the owner of a `ViewModelStore`, typically a `ViewModelStoreOwner`, is destroyed and recreated due to a configuration change, the new owner must have the old instance of the `ViewModelStore`.

If the owner of a `ViewModelStore` is destroyed and is *not* going to be recreated, it should call `clear` on this `ViewModelStore` so that The `ViewModel`s stored by it are notified that they are no longer needed.

Use `ViewModelStoreOwner.getViewModelStore` to retrieve a `ViewModelStore` for activities and fragments.

[linux, macos, mingw, tvos, wasmJs, watchos]\
actual open class [ViewModelStore](index.md)

[android, ios, js, jvm]\
actual typealias [ViewModelStore](index.md) = [androidx.lifecycle.ViewModelStore](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStore.html)

## Constructors

| | |
|---|---|
| [ViewModelStore](-view-model-store.md) | [linux, macos, mingw, tvos, wasmJs, watchos]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | [common]<br>expect fun [clear](clear.md)()<br>Clears internal storage and notifies `ViewModel`s that they are no longer used.<br>[linux, macos, mingw, tvos, wasmJs, watchos]<br>[linux, macos, mingw, tvos, wasmJs, watchos]<br>actual fun [clear](clear.md)() |
| [get](../../../../statex-viewmodel/statex-viewmodel/com.kodetools.statex.viewmodel/-view-model-store/[watchos]get.md) | [linux, macos, mingw, tvos, wasmJs, watchos]<br>[linux]<br>operator fun [get]([linux]get.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [PlatformViewModel](../-platform-view-model/index.md)?<br>[macos]<br>operator fun [get]([macos]get.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [PlatformViewModel](../-platform-view-model/index.md)?<br>[mingw]<br>operator fun [get]([mingw]get.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [PlatformViewModel](../-platform-view-model/index.md)?<br>[tvos]<br>operator fun [get]([tvos]get.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [PlatformViewModel](../-platform-view-model/index.md)?<br>[wasmJs]<br>operator fun [get]([wasm-js]get.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [PlatformViewModel](../-platform-view-model/index.md)?<br>[watchos]<br>operator fun [get]([watchos]get.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [PlatformViewModel](../-platform-view-model/index.md)?<br>Returns the `ViewModel` mapped to the given `key` or null if none exists. |
| [keys](../../../../statex-viewmodel/statex-viewmodel/com.kodetools.statex.viewmodel/-view-model-store/[watchos]keys.md) | [linux, macos, mingw, tvos, wasmJs, watchos]<br>[linux]<br>fun [keys]([linux]keys.md)(): [Set](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>[macos]<br>fun [keys]([macos]keys.md)(): [Set](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>[mingw]<br>fun [keys]([mingw]keys.md)(): [Set](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>[tvos]<br>fun [keys]([tvos]keys.md)(): [Set](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>[wasmJs]<br>fun [keys]([wasm-js]keys.md)(): [Set](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt;<br>[watchos]<br>fun [keys]([watchos]keys.md)(): [Set](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [put](../../../../statex-viewmodel/statex-viewmodel/com.kodetools.statex.viewmodel/-view-model-store/[watchos]put.md) | [linux, macos, mingw, tvos, wasmJs, watchos]<br>[linux]<br>fun [put]([linux]put.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), viewModel: [PlatformViewModel](../-platform-view-model/index.md))<br>[macos]<br>fun [put]([macos]put.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), viewModel: [PlatformViewModel](../-platform-view-model/index.md))<br>[mingw]<br>fun [put]([mingw]put.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), viewModel: [PlatformViewModel](../-platform-view-model/index.md))<br>[tvos]<br>fun [put]([tvos]put.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), viewModel: [PlatformViewModel](../-platform-view-model/index.md))<br>[wasmJs]<br>fun [put]([wasm-js]put.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), viewModel: [PlatformViewModel](../-platform-view-model/index.md))<br>[watchos]<br>fun [put]([watchos]put.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), viewModel: [PlatformViewModel](../-platform-view-model/index.md)) |
