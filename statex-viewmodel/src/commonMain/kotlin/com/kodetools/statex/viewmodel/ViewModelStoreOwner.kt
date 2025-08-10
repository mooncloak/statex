package com.kodetools.statex.viewmodel

/**
 * A scope that owns [ViewModelStore].
 *
 * A responsibility of an implementation of this interface is to retain owned ViewModelStore during
 * the configuration changes and call [ViewModelStore.clear], when this scope is going to be
 * destroyed.
 */
public expect interface ViewModelStoreOwner {

    /** The owned [ViewModelStore] */
    public val viewModelStore: ViewModelStore
}
