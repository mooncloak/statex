package com.mooncloak.kodetools.statex

import androidx.compose.runtime.State

interface ViewModelLifecycleScope {

    val isBound: State<Boolean>

    fun bind()

    fun unbind()
}
