package com.mooncloak.kodetools.statex

import kotlinx.coroutines.CoroutineScope

/**
 * An internal base "ViewModel" component that delegates to any such platform-specific component. This allows the
 * [ViewModel] component from this library to extend from and inherit any platform-specific ViewModel functionality.
 * For example, on Android, there is the `androidx.lifecycle.ViewModel` class which provides functionality useful for
 * Android development. Therefore, the Android implementation of this component inherits from the
 * `androidx.lifecycle.ViewModel` component.
 */
public expect abstract class PlatformViewModel internal constructor() {

    protected open val viewModelScope: CoroutineScope
}
