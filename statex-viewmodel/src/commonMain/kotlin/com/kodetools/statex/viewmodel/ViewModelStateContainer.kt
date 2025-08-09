package com.kodetools.statex.viewmodel

import com.kodetools.statex.container.MutableStateContainer
import com.kodetools.statex.container.StateContainer

/**
 * A [StateContainer] that is directly associated with a [ViewModel] instance. It is used so that mutation operations
 * only happen within [ViewModel] subclasses and read-only operations are exposed publicly through the [ViewModel] by
 * default.
 *
 * @see [ViewModel.viewModelStateContainerOf] to create an instance of this class.
 */
public class ViewModelStateContainer<T> internal constructor(
    internal val delegate: MutableStateContainer<T>
) : StateContainer<T> by delegate
