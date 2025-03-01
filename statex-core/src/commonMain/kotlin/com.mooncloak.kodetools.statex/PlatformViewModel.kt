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

    /**
     * The [CoroutineScope] for this platform viewmodel instance. This is nullable because the platform might not
     * provide a value here, in which case, the [ViewModel] component that extends this [PlatformViewModel] must
     * handle the creation of the coroutine scope.
     *
     * > [!Note] We call this "coroutineScope" instead of "viewModelScope" to avoid name clashes on platforms whose
     * > implementation already has a property called that.
     */
    protected val coroutineScope: CoroutineScope?
}
