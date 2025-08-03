package com.mooncloak.kodetools.statex

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

// TODO: Re-enable multiplatform viewmodel library support. Currently it causes runtime issues with WASM.
public actual abstract class PlatformViewModel internal actual constructor() {

    protected actual open val viewModelScope: CoroutineScope =
        object : CoroutineScope {

            override val coroutineContext: CoroutineContext
                get() = SupervisorJob() + Dispatchers.Main
        }
}
