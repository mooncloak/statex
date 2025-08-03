package com.mooncloak.kodetools.statex.persistence

import com.russhwolf.settings.Settings

@PublishedApi
@ExperimentalPersistentStateAPI
internal actual fun PersistentStorage.Companion.platformDefault(): PersistentStorage =
    SettingsPersistentStorage(settings = Settings())
