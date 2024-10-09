//[statex-persistence](../../../index.md)/[com.mooncloak.kodetools.statex.persistence](../index.md)/[SerialFormatSaver](index.md)

# SerialFormatSaver

interface [SerialFormatSaver](index.md)&lt;[Original](index.md), [Saveable](index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; : [Saver](https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/Saver.html)&lt;[Original](index.md), [Saveable](index.md)&gt; 

#### Inheritors

| |
|---|
| [StringFormatSaver](../-string-format-saver/index.md) |
| [BinaryFormatByteArraySaver](../-binary-format-byte-array-saver/index.md) |
| [BinaryFormatHexStringSaver](../-binary-format-hex-string-saver/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [format](format.md) | [common]<br>abstract val [format](format.md): SerialFormat |

## Functions

| Name | Summary |
|---|---|
| [restore](index.md#514579959%2FFunctions%2F1961133779) | [common]<br>abstract fun [restore](index.md#514579959%2FFunctions%2F1961133779)(value: [Saveable](index.md)): [Original](index.md)? |
| [save](index.md#2133965050%2FFunctions%2F1961133779) | [common]<br>abstract fun [SaverScope](https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/SaverScope.html).[save](index.md#2133965050%2FFunctions%2F1961133779)(value: [Original](index.md)): [Saveable](index.md)? |
