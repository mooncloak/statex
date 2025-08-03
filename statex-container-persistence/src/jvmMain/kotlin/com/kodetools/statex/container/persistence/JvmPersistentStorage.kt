package com.kodetools.statex.container.persistence

import com.russhwolf.settings.Settings

@PublishedApi
@ExperimentalPersistentStateAPI
internal actual fun PersistentStorage.Companion.platformDefault(): PersistentStorage =
    SettingsPersistentStorage(settings = Settings())
