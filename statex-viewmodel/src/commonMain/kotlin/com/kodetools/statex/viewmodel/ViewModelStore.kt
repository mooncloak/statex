package com.kodetools.statex.viewmodel

/**
 * Class to store `ViewModel`s.
 *
 * Instances of `ViewModelStore` must be retained through configuration changes. If the owner of a
 * `ViewModelStore`, typically a [`ViewModelStoreOwner`], is destroyed and recreated due to a
 * configuration change, the new owner must have the old instance of the `ViewModelStore`.
 *
 * If the owner of a `ViewModelStore` is destroyed and is _not_ going to be recreated, it should
 * call [`clear`] on this `ViewModelStore` so that The `ViewModel`s stored by it are notified that
 * they are no longer needed.
 *
 * Use [`ViewModelStoreOwner.getViewModelStore`] to retrieve a `ViewModelStore` for activities and
 * fragments.
 */
public expect open class ViewModelStore {

    /**
     * Clears internal storage and notifies `ViewModel`s that they are no longer used.
     */
    public fun clear()
}
