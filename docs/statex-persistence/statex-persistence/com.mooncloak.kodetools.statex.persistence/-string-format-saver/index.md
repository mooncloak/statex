//[statex-persistence](../../../index.md)/[com.mooncloak.kodetools.statex.persistence](../index.md)/[StringFormatSaver](index.md)

# StringFormatSaver

[common]\
class [StringFormatSaver](index.md)&lt;[Value](index.md)&gt;(val format: StringFormat, serializer: KSerializer&lt;[Value](index.md)&gt;) : [SerialFormatSaver](../-serial-format-saver/index.md)&lt;[Value](index.md), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

## Constructors

| | |
|---|---|
| [StringFormatSaver](-string-format-saver.md) | [common]<br>constructor(format: StringFormat, serializer: KSerializer&lt;[Value](index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [format](format.md) | [common]<br>open override val [format](format.md): StringFormat |

## Functions

| Name | Summary |
|---|---|
| [restore](restore.md) | [common]<br>open override fun [restore](restore.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Value](index.md)? |
| [save](save.md) | [common]<br>open override fun [SaverScope](https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/SaverScope.html).[save](save.md)(value: [Value](index.md)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
