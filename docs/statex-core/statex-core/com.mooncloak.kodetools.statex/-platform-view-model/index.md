//[statex-core](../../../index.md)/[com.mooncloak.kodetools.statex](../index.md)/[PlatformViewModel](index.md)

# PlatformViewModel

expect abstract class [PlatformViewModel](index.md)actual abstract class [PlatformViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)actual abstract class [PlatformViewModel](index.md)actual abstract class [PlatformViewModel](index.md)actual abstract class [PlatformViewModel](index.md)actual abstract class [PlatformViewModel](index.md)

An internal base &quot;ViewModel&quot; component that delegates to any such platform-specific component. This allows the [ViewModel](../-view-model/index.md) component from this library to extend from and inherit any platform-specific ViewModel functionality. For example, on Android, there is the `androidx.lifecycle.ViewModel` class which provides functionality useful for Android development. Therefore, the Android implementation of this component inherits from the `androidx.lifecycle.ViewModel` component.

#### Inheritors

| |
|---|
| [ViewModel](../-view-model/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#383812252%2FFunctions%2F596112941) | [android]<br>open fun [addCloseable](index.md#383812252%2FFunctions%2F596112941)(closeable: [AutoCloseable](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html))<br>fun [addCloseable](index.md#1722490497%2FFunctions%2F596112941)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html)) |
| [getCloseable](index.md#1102255800%2FFunctions%2F596112941) | [android]<br>fun &lt;[T](index.md#1102255800%2FFunctions%2F596112941) : [AutoCloseable](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html)&gt; [getCloseable](index.md#1102255800%2FFunctions%2F596112941)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [T](index.md#1102255800%2FFunctions%2F596112941)? |
