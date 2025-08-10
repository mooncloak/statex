package com.kodetools.statex.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

public actual abstract class PlatformViewModel {

    private val coroutineScope: CoroutineScope

    public actual constructor() : super() {
        coroutineScope = object : CoroutineScope {

            override val coroutineContext: CoroutineContext
                get() = SupervisorJob() + Dispatchers.Main
        }
    }

    public actual constructor(viewModelScope: CoroutineScope) : super() {
        coroutineScope = viewModelScope
    }

    private val keyedCloseables = mutableMapOf<String, AutoCloseable>()
    private val closeables = mutableListOf<AutoCloseable>()

    protected actual open val viewModelScope: CoroutineScope
        get() = coroutineScope

    protected actual open fun onCleared() {
    }

    public actual fun addCloseable(key: String, closeable: AutoCloseable) {
        keyedCloseables[key] = closeable
    }

    public actual fun addCloseable(closeable: AutoCloseable) {
        closeables.add(closeable)
    }

    @Suppress("UNCHECKED_CAST")
    public actual fun <T : AutoCloseable> getCloseable(key: String): T? =
        keyedCloseables[key] as? T

    internal fun clear() {
        closeables.forEach { it.close() }
        keyedCloseables.forEach { it.value.close() }
        closeables.clear()
        keyedCloseables.clear()
        onCleared()
    }
}
