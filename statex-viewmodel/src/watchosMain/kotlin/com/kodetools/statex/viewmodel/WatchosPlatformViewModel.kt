package com.kodetools.statex.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

public actual abstract class PlatformViewModel internal actual constructor() {

    protected actual open val viewModelScope: CoroutineScope =
        object : CoroutineScope {

            override val coroutineContext: CoroutineContext
                get() = SupervisorJob() + Dispatchers.Main
        }
}
