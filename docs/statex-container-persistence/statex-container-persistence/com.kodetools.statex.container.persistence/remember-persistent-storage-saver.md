//[statex-container-persistence](../../index.md)/[com.kodetools.statex.container.persistence](index.md)/[rememberPersistentStorageSaver](remember-persistent-storage-saver.md)

# rememberPersistentStorageSaver

[common]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

inline fun &lt;[T](remember-persistent-storage-saver.md)&gt; [rememberPersistentStorageSaver](remember-persistent-storage-saver.md)(vararg inputs: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)?, key: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, storage: [PersistentStorage](-persistent-storage/index.md) = PersistentStorage.platformDefault(), serializersModule: SerializersModule = EmptySerializersModule(), serializer: KSerializer&lt;[T](remember-persistent-storage-saver.md)&gt; = serializersModule.serializer(), coroutineScope: CoroutineScope = rememberCoroutineScope(), noinline defaultValue: () -&gt; [T](remember-persistent-storage-saver.md)): [Saver](https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/Saver.html)&lt;[T](remember-persistent-storage-saver.md), out [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)&gt;
