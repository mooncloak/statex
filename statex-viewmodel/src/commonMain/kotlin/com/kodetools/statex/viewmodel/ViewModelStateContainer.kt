package com.kodetools.statex.viewmodel

import com.kodetools.statex.container.MutableStateContainer
import com.kodetools.statex.container.StateContainer

public class ViewModelStateContainer<T> internal constructor(
    internal val delegate: MutableStateContainer<T>
) : StateContainer<T> by delegate
