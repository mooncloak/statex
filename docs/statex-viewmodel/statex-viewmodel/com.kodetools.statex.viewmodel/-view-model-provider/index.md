//[statex-viewmodel](../../../index.md)/[com.kodetools.statex.viewmodel](../index.md)/[ViewModelProvider](index.md)

# ViewModelProvider

[common]\
expect class [ViewModelProvider](index.md)

[linux, macos, mingw, tvos, wasmJs, watchos]\
actual open class [ViewModelProvider](index.md)

[android, ios, js, jvm]\
actual typealias [ViewModelProvider](index.md) = [androidx.lifecycle.ViewModelProvider](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider.html)

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common, linux, macos, mingw, tvos, wasmJs, watchos]<br>[common]<br>expect object [Companion](-companion/index.md)<br>[linux, macos, mingw, tvos, wasmJs, watchos]<br>actual object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [get](../get.md) | [android, ios, js, jvm, common, linux, macos, mingw, tvos, wasmJs, watchos]<br>[android, ios, js, jvm, linux, macos, mingw, tvos, wasmJs, watchos]<br>actual operator fun &lt;[T](../get.md) : [PlatformViewModel](../-platform-view-model/index.md)&gt; [ViewModelProvider](index.md).[get](../get.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;[T](../get.md)&gt;): [T](../get.md)<br>[common]<br>expect operator fun &lt;[T](../get.md) : [PlatformViewModel](../-platform-view-model/index.md)&gt; [ViewModelProvider](index.md).[get](../get.md)(modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;[T](../get.md)&gt;): [T](../get.md)<br>[android, ios, js, jvm, linux, macos, mingw, tvos, wasmJs, watchos]<br>actual operator fun &lt;[T](../get.md) : [PlatformViewModel](../-platform-view-model/index.md)&gt; [ViewModelProvider](index.md).[get](../get.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;[T](../get.md)&gt;): [T](../get.md)<br>[common]<br>expect operator fun &lt;[T](../get.md) : [PlatformViewModel](../-platform-view-model/index.md)&gt; [ViewModelProvider](index.md).[get](../get.md)(key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), modelClass: [KClass](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;[T](../get.md)&gt;): [T](../get.md) |
