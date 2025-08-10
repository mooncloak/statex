//[statex-viewmodel](../../index.md)/[com.kodetools.statex.viewmodel](index.md)/[createMutableCreationExtras](create-mutable-creation-extras.md)

# createMutableCreationExtras

[android, ios, js, jvm, linux, macos, mingw, tvos, wasmJs, watchos]\
[android, ios, js, jvm]\
actual inline fun [createMutableCreationExtras](create-mutable-creation-extras.md)(initialExtras: [CreationExtras](-creation-extras/index.md)): [MutableCreationExtras](-mutable-creation-extras/index.md)

[linux, macos, mingw, tvos, wasmJs, watchos]\
actual fun [createMutableCreationExtras](create-mutable-creation-extras.md)(initialExtras: [CreationExtras](-creation-extras/index.md)): [MutableCreationExtras](-mutable-creation-extras/index.md)

[common]\
expect fun [createMutableCreationExtras](create-mutable-creation-extras.md)(initialExtras: [CreationExtras](-creation-extras/index.md) = CreationExtrasEmpty): [MutableCreationExtras](-mutable-creation-extras/index.md)

Constructs a [MutableCreationExtras](-mutable-creation-extras/index.md) containing the elements of the specified [initialExtras](create-mutable-creation-extras.md), in the order they are returned by the [CreationExtras](-creation-extras/index.md)'s iterator.
