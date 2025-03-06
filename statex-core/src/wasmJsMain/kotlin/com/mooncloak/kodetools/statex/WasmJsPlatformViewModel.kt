package com.mooncloak.kodetools.statex

import kotlinx.coroutines.CoroutineScope

// TODO: Re-enable multiplatform viewmodel library support. Currently it causes runtime issues with WASM.
public actual abstract class PlatformViewModel internal actual constructor() {

    protected actual open val coroutineScope: CoroutineScope? = null
}
