package com.mooncloak.kodetools.statex

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * A default [MutableStateContainer] implementation.
 */
@Stable
internal class DefaultMutableStateContainer<T> internal constructor(
    initialValue: T
) : MutableStateContainer<T> {

    override val initial: State<T>
        get() = mutableInitial

    override val current: State<T>
        get() = mutableCurrent

    override val stream: StateFlow<T>
        get() = mutableFlow.asStateFlow()

    override val changed: State<Boolean>
        get() = mutableChanged

    private val mutableInitial = mutableStateOf(initialValue)
    private val mutableCurrent = mutableStateOf(initialValue)
    private val mutableChanged = mutableStateOf(false)
    private val mutableFlow = MutableStateFlow(value = initialValue)

    private val mutex = Mutex(locked = false)

    override suspend fun change(value: T) {
        mutex.withLock {
            if (value != current.value) {
                mutableCurrent.value = value
                mutableChanged.value = true
                mutableFlow.value = value
            }
        }
    }

    override suspend fun reset(initialValue: T) {
        mutex.withLock {
            mutableInitial.value = initialValue
            mutableCurrent.value = initialValue
            mutableChanged.value = false
            mutableFlow.value = initialValue
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DefaultMutableStateContainer<*>) return false

        if (mutableInitial != other.mutableInitial) return false
        if (mutableCurrent != other.mutableCurrent) return false
        if (mutableChanged != other.mutableChanged) return false
        if (mutableFlow != other.mutableFlow) return false

        return mutex == other.mutex
    }

    override fun hashCode(): Int {
        var result = mutableInitial.hashCode()
        result = 31 * result + mutableCurrent.hashCode()
        result = 31 * result + mutableChanged.hashCode()
        result = 31 * result + mutableFlow.hashCode()
        result = 31 * result + mutex.hashCode()
        return result
    }

    override fun toString(): String =
        "DefaultMutableStateContainer(initial=$initial, current=$current, flow=$stream, changed=$changed)"
}
