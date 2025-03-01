package com.mooncloak.kodetools.statex

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

public actual abstract class PlatformViewModel internal actual constructor() : androidx.lifecycle.ViewModel() {

    protected actual open val coroutineScope: CoroutineScope?
        get() = this.viewModelScope
}
