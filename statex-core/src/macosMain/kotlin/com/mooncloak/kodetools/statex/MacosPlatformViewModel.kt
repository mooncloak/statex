package com.mooncloak.kodetools.statex

import kotlinx.coroutines.CoroutineScope

public actual abstract class PlatformViewModel internal actual constructor() {

    protected actual open val coroutineScope: CoroutineScope?
        get() = null
}
