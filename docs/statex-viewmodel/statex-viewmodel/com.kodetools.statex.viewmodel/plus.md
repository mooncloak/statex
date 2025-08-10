//[statex-viewmodel](../../index.md)/[com.kodetools.statex.viewmodel](index.md)/[plus](plus.md)

# plus

[android, ios, js, jvm, linux, macos, mingw, tvos, wasmJs, watchos]\
[android, ios, js, jvm]\
actual inline operator fun [CreationExtras](-creation-extras/index.md).[plus](plus.md)(creationExtras: [CreationExtras](-creation-extras/index.md)): [MutableCreationExtras](-mutable-creation-extras/index.md)

[linux, macos, mingw, tvos, wasmJs, watchos]\
actual operator fun [CreationExtras](-creation-extras/index.md).[plus](plus.md)(creationExtras: [CreationExtras](-creation-extras/index.md)): [MutableCreationExtras](-mutable-creation-extras/index.md)

[common]\
expect operator fun [CreationExtras](-creation-extras/index.md).[plus](plus.md)(creationExtras: [CreationExtras](-creation-extras/index.md)): [MutableCreationExtras](-mutable-creation-extras/index.md)

Creates a new read-only [CreationExtras](-creation-extras/index.md) by replacing or adding entries to [this](../../../statex-viewmodel/com.kodetools.statex.viewmodel/index.md) extras from another [creationExtras](plus.md).

The returned [CreationExtras](-creation-extras/index.md) preserves the entry iteration order of the original [CreationExtras](-creation-extras/index.md).

Those entries of another [creationExtras](plus.md) that are missing in [this](../../../statex-viewmodel/com.kodetools.statex.viewmodel/index.md) extras are iterated in the end in the order of that [creationExtras](plus.md).
