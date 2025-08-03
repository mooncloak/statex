package com.kodetools.statex.container.persistence

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.Settings

@OptIn(ExperimentalSettingsImplementation::class)
@PublishedApi
@ExperimentalPersistentStateAPI
internal actual fun PersistentStorage.Companion.platformDefault(): PersistentStorage =
    SettingsPersistentStorage(settings = Settings())
