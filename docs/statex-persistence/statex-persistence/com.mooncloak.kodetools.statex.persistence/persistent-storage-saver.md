//[statex-persistence](../../index.md)/[com.mooncloak.kodetools.statex.persistence](index.md)/[persistentStorageSaver](persistent-storage-saver.md)

# persistentStorageSaver

[common]\
inline fun &lt;[T](persistent-storage-saver.md)&gt; [persistentStorageSaver](persistent-storage-saver.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), storage: [PersistentStorage](-persistent-storage/index.md) = PersistentStorage.platformDefault(), serializersModule: SerializersModule = EmptySerializersModule(), serializer: KSerializer&lt;[T](persistent-storage-saver.md)&gt; = serializersModule.serializer(), coroutineScope: CoroutineScope, noinline defaultValue: () -&gt; [T](persistent-storage-saver.md)): [Saver](https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/Saver.html)&lt;[T](persistent-storage-saver.md), out [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;
