package com.mooncloak.kodetools.statex.persistence

@PublishedApi
@ExperimentalPersistentStateAPI
internal actual fun PersistentStorage.Companion.platformDefault(): PersistentStorage =
    throw UnsupportedOperationException("Currently, there is no default PersistentStorage implementation for linux. Use a custom PersistentStorage implementation instead.")
