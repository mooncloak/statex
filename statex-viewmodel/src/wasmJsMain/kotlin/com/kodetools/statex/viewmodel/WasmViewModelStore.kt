package com.kodetools.statex.viewmodel

public actual open class ViewModelStore {

    private val map = mutableMapOf<String, PlatformViewModel>()

    public fun put(key: String, viewModel: PlatformViewModel) {
        val oldViewModel = map.put(key, viewModel)
        oldViewModel?.clear()
    }

    /**
     * Returns the `ViewModel` mapped to the given `key` or null if none exists.
     */
    public operator fun get(key: String): PlatformViewModel? {
        return map[key]
    }

    public fun keys(): Set<String> {
        return HashSet(map.keys)
    }

    public actual fun clear() {
        for (vm in map.values) {
            vm.clear()
        }
        map.clear()
    }
}
