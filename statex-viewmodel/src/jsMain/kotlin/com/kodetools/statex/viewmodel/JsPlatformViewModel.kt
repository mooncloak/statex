package com.kodetools.statex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope as platformViewModelScope
import kotlinx.coroutines.CoroutineScope

public actual abstract class PlatformViewModel : ViewModel {

    public actual constructor() : super()

    public actual constructor(viewModelScope: CoroutineScope) : super(viewModelScope)

    protected actual open val viewModelScope: CoroutineScope
        get() = this.platformViewModelScope
}
