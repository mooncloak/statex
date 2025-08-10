package com.kodetools.statex.viewmodel

import androidx.lifecycle.ViewModelProvider.Companion.create as platformViewModelProviderCreate
import androidx.lifecycle.ViewModelProvider.Companion.VIEW_MODEL_KEY as platformViewModelKey
import kotlin.reflect.KClass

public actual typealias ViewModelProvider = androidx.lifecycle.ViewModelProvider
public actual typealias ViewModelProviderFactory = androidx.lifecycle.ViewModelProvider.Factory

public actual operator fun <T : PlatformViewModel> ViewModelProvider.get(modelClass: KClass<T>): T =
    this[modelClass]

public actual operator fun <T : PlatformViewModel> ViewModelProvider.get(key: String, modelClass: KClass<T>): T =
    this[key, modelClass]

public actual fun ViewModelProvider.Companion.create(
    owner: ViewModelStoreOwner,
    factory: ViewModelProviderFactory,
    extras: CreationExtras,
): ViewModelProvider = platformViewModelProviderCreate(
    owner = owner,
    factory = factory,
    extras = extras
)

public actual fun ViewModelProvider.Companion.create(
    store: ViewModelStore,
    factory: ViewModelProviderFactory,
    extras: CreationExtras
): ViewModelProvider = platformViewModelProviderCreate(
    store = store,
    factory = factory,
    extras = extras
)

public actual val ViewModelProvider.Companion.VIEW_MODEL_KEY: CreationExtrasKey<String>
    get() = platformViewModelKey

public actual fun <T : PlatformViewModel> ViewModelProviderFactory.create(
    modelClass: KClass<T>,
    extras: CreationExtras,
): T = this.create(modelClass, extras)
