//[statex-persistence](../../../index.md)/[com.mooncloak.kodetools.statex.persistence](../index.md)/[PersistentStorage](index.md)

# PersistentStorage

[common]\
interface [PersistentStorage](index.md)

An abstraction around a persistent key-value storage.

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [get](get.md) | [common]<br>abstract fun &lt;[T](get.md)&gt; [get](get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), defaultValue: [T](get.md), serializersModule: SerializersModule = EmptySerializersModule(), serializer: KSerializer&lt;[T](get.md)&gt;): [T](get.md) |
| [get](../get.md) | [common]<br>inline fun &lt;[T](../get.md)&gt; [PersistentStorage](index.md).[get](../get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), defaultValue: [T](../get.md), serializersModule: SerializersModule = EmptySerializersModule()): [T](../get.md) |
| [set](set.md) | [common]<br>abstract suspend fun &lt;[T](set.md)&gt; [set](set.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [T](set.md), serializersModule: SerializersModule = EmptySerializersModule(), serializer: KSerializer&lt;[T](set.md)&gt;) |
| [set](../set.md) | [common]<br>inline suspend fun &lt;[T](../set.md)&gt; [PersistentStorage](index.md).[set](../set.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [T](../set.md), serializersModule: SerializersModule = EmptySerializersModule()) |
