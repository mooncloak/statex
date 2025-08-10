//[statex-viewmodel](../../../index.md)/[com.kodetools.statex.viewmodel](../index.md)/[ViewModelStoreOwner](index.md)

# ViewModelStoreOwner

[common]\
expect interface [ViewModelStoreOwner](index.md)

A scope that owns [ViewModelStore](../-view-model-store/index.md).

A responsibility of an implementation of this interface is to retain owned ViewModelStore during the configuration changes and call [ViewModelStore.clear](../-view-model-store/clear.md), when this scope is going to be destroyed.

[linux, macos, mingw, tvos, wasmJs, watchos]\
actual interface [ViewModelStoreOwner](index.md)

[android, ios, js, jvm]\
actual typealias [ViewModelStoreOwner](index.md) = [androidx.lifecycle.ViewModelStoreOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStoreOwner.html)

## Properties

| Name | Summary |
|---|---|
| [viewModelStore](view-model-store.md) | [common]<br>expect abstract val [viewModelStore](view-model-store.md): [ViewModelStore](../-view-model-store/index.md)<br>The owned [ViewModelStore](../-view-model-store/index.md)<br>[linux, macos, mingw, tvos, wasmJs, watchos]<br>[linux, macos, mingw, tvos, wasmJs, watchos]<br>actual abstract val [viewModelStore](view-model-store.md): [ViewModelStore](../-view-model-store/index.md) |
