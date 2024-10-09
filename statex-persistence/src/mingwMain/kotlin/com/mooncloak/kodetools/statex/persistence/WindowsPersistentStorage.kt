package com.mooncloak.kodetools.statex.persistence

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.Settings

@OptIn(ExperimentalSettingsImplementation::class)
@PublishedApi
internal actual fun PersistentStorage.Companion.platformDefault(): PersistentStorage =
    SettingsPersistentStorage(settings = Settings())
