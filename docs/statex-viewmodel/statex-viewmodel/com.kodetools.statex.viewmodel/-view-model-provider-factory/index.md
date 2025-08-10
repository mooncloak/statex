//[statex-viewmodel](../../../index.md)/[com.kodetools.statex.viewmodel](../index.md)/[ViewModelProviderFactory](index.md)

# ViewModelProviderFactory

[common]\
expect interface [ViewModelProviderFactory](index.md)

[linux, macos, mingw, tvos, wasmJs, watchos]\
actual interface [ViewModelProviderFactory](index.md)

[android, ios, js, jvm]\
actual typealias [ViewModelProviderFactory](index.md) = [ViewModelProvider.Factory](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider.Factory.html)

## Functions

| Name | Summary |
|---|---|
| [create](../create.md) | [android, ios, js, jvm, common, linux, macos, mingw, tvos, wasmJs, watchos]<br>[android, ios, js, jvm, linux, macos, mingw, tvos, wasmJs, watchos]<br>actual fun &lt;[T](../create.md) : [PlatformViewModel](../-platform-view-model/index.md)&gt; [ViewModelProviderFactory](index.md).[create](../create.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;[T](../create.md)&gt;, extras: [CreationExtras](../-creation-extras/index.md)): [T](../create.md)<br>[common]<br>expect fun &lt;[T](../create.md) : [PlatformViewModel](../-platform-view-model/index.md)&gt; [ViewModelProviderFactory](index.md).[create](../create.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;[T](../create.md)&gt;, extras: [CreationExtras](../-creation-extras/index.md)): [T](../create.md) |
| [createInstance](../../../../statex-viewmodel/statex-viewmodel/com.kodetools.statex.viewmodel/-view-model-provider-factory/[watchos]create-instance.md) | [linux, macos, mingw, tvos, wasmJs, watchos]<br>[linux]<br>abstract fun [createInstance]([linux]create-instance.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;out [PlatformViewModel](../-platform-view-model/index.md)&gt;, extras: [CreationExtras](../-creation-extras/index.md)): [PlatformViewModel](../-platform-view-model/index.md)<br>[macos]<br>abstract fun [createInstance]([macos]create-instance.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;out [PlatformViewModel](../-platform-view-model/index.md)&gt;, extras: [CreationExtras](../-creation-extras/index.md)): [PlatformViewModel](../-platform-view-model/index.md)<br>[mingw]<br>abstract fun [createInstance]([mingw]create-instance.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;out [PlatformViewModel](../-platform-view-model/index.md)&gt;, extras: [CreationExtras](../-creation-extras/index.md)): [PlatformViewModel](../-platform-view-model/index.md)<br>[tvos]<br>abstract fun [createInstance]([tvos]create-instance.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;out [PlatformViewModel](../-platform-view-model/index.md)&gt;, extras: [CreationExtras](../-creation-extras/index.md)): [PlatformViewModel](../-platform-view-model/index.md)<br>[wasmJs]<br>abstract fun [createInstance]([wasm-js]create-instance.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;out [PlatformViewModel](../-platform-view-model/index.md)&gt;, extras: [CreationExtras](../-creation-extras/index.md)): [PlatformViewModel](../-platform-view-model/index.md)<br>[watchos]<br>abstract fun [createInstance]([watchos]create-instance.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;out [PlatformViewModel](../-platform-view-model/index.md)&gt;, extras: [CreationExtras](../-creation-extras/index.md)): [PlatformViewModel](../-platform-view-model/index.md) |
