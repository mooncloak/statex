package com.mooncloak.kodetools.statex

import androidx.lifecycle.viewModelScope as platformViewModelScope
import kotlinx.coroutines.CoroutineScope

public actual abstract class PlatformViewModel internal actual constructor() :
    androidx.lifecycle.ViewModel() {

    protected actual open val viewModelScope: CoroutineScope
        get() = this.platformViewModelScope
}
