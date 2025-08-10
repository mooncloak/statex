package com.kodetools.statex.viewmodel

import kotlin.reflect.KClass

public expect class ViewModelProvider {

    public companion object
}

public expect operator fun <T : PlatformViewModel> ViewModelProvider.get(modelClass: KClass<T>): T

public expect operator fun <T : PlatformViewModel> ViewModelProvider.get(key: String, modelClass: KClass<T>): T

public expect fun ViewModelProvider.Companion.create(
    owner: ViewModelStoreOwner,
    factory: ViewModelProviderFactory,
    extras: CreationExtras,
): ViewModelProvider

public expect fun ViewModelProvider.Companion.create(
    store: ViewModelStore,
    factory: ViewModelProviderFactory,
    extras: CreationExtras
): ViewModelProvider

public expect val ViewModelProvider.Companion.VIEW_MODEL_KEY: CreationExtrasKey<String>

public expect interface ViewModelProviderFactory

public expect fun <T : PlatformViewModel> ViewModelProviderFactory.create(
    modelClass: KClass<T>,
    extras: CreationExtras,
): T
