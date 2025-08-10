package com.kodetools.statex.viewmodel

import kotlin.reflect.KClass

public actual open class ViewModelProvider internal constructor(
    internal val impl: ViewModelProviderImpl,
) {

    internal fun <T : PlatformViewModel> getByClass(modelClass: KClass<T>): T = impl.getByClass(modelClass)
    internal fun <T : PlatformViewModel> getByKey(key: String, modelClass: KClass<T>): T =
        impl.getByKey(key, modelClass)

    public actual companion object
}

private val singletonViewModelKey = object : CreationExtrasKey<String> {}

public actual val ViewModelProvider.Companion.VIEW_MODEL_KEY: CreationExtrasKey<String>
    get() = singletonViewModelKey

public actual fun ViewModelProvider.Companion.create(
    owner: ViewModelStoreOwner,
    factory: ViewModelProviderFactory,
    extras: CreationExtras,
): ViewModelProvider = ViewModelProvider(
    ViewModelProviderImpl(
        store = owner.viewModelStore,
        factory = factory,
        defaultCreationExtras = extras,
    )
)

public actual fun ViewModelProvider.Companion.create(
    store: ViewModelStore,
    factory: ViewModelProviderFactory,
    extras: CreationExtras,
): ViewModelProvider = ViewModelProvider(
    ViewModelProviderImpl(
        store = store,
        factory = factory,
        defaultCreationExtras = extras,
    )
)

public actual operator fun <T : PlatformViewModel> ViewModelProvider.get(modelClass: KClass<T>): T =
    this.getByClass(modelClass)

public actual operator fun <T : PlatformViewModel> ViewModelProvider.get(
    key: String,
    modelClass: KClass<T>
): T = this.getByKey(key, modelClass)

public actual interface ViewModelProviderFactory {

    public fun createInstance(modelClass: KClass<out PlatformViewModel>, extras: CreationExtras): PlatformViewModel
}

@Suppress("UNCHECKED_CAST")
public actual fun <T : PlatformViewModel> ViewModelProviderFactory.create(
    modelClass: KClass<T>,
    extras: CreationExtras,
): T = this.createInstance(modelClass, extras) as T

internal class ViewModelProviderImpl internal constructor(
    private val store: ViewModelStore,
    private val factory: ViewModelProviderFactory,
    private val defaultCreationExtras: CreationExtras,
) {

    fun <T : PlatformViewModel> getByClass(modelClass: KClass<T>): T {
        val explicitKey = defaultCreationExtras[ViewModelProvider.VIEW_MODEL_KEY]
        val key = explicitKey ?: defaultKey(modelClass)

        return getByKey(key, modelClass)
    }

    fun <T : PlatformViewModel> getByKey(key: String, modelClass: KClass<T>): T {
        // Reuse existing instance if present
        val existing = store[key]
        if (existing != null) {
            if (!modelClass.isInstance(existing)) {
                error("Stored ViewModel for key '$key' is of type ${existing::class}, not ${modelClass}.")
            }
            @Suppress("UNCHECKED_CAST")
            return existing as T
        }

        // Merge default extras with the computed key
        val creationExtras = MutableCreationExtras(defaultCreationExtras).apply {
            this[ViewModelProvider.VIEW_MODEL_KEY] = key
        }

        val newVm = factory.create(modelClass, creationExtras)
        store.put(key, newVm)

        return newVm
    }

    private fun defaultKey(modelClass: KClass<*>): String {
        val name = modelClass.qualifiedName
            ?: error("Local/anonymous classes cannot be used as ViewModels. Class: $modelClass")

        return "com.kodetools.statex.viewmodel.DefaultKey:$name"
    }
}
