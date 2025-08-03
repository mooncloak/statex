package com.kodetools.statex.container.persistence

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.encodeValue
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

/**
 * An abstraction around a persistent key-value storage.
 */
@ExperimentalPersistentStateAPI
public interface PersistentStorage {

    public fun <T> get(
        key: String,
        defaultValue: T,
        serializersModule: SerializersModule = EmptySerializersModule(),
        serializer: KSerializer<T>
    ): T

    public suspend fun <T> set(
        key: String,
        value: T,
        serializersModule: SerializersModule = EmptySerializersModule(),
        serializer: KSerializer<T>
    )

    public companion object
}

@ExperimentalPersistentStateAPI
public inline fun <reified T> PersistentStorage.get(
    key: String,
    defaultValue: T,
    serializersModule: SerializersModule = EmptySerializersModule()
): T = this.get(
    key = key,
    defaultValue = defaultValue,
    serializersModule = serializersModule,
    serializer = serializersModule.serializer()
)

@ExperimentalPersistentStateAPI
public suspend inline fun <reified T> PersistentStorage.set(
    key: String,
    value: T,
    serializersModule: SerializersModule = EmptySerializersModule()
) {
    this.set(
        key = key,
        value = value,
        serializersModule = serializersModule,
        serializer = serializersModule.serializer()
    )
}

/**
 * Retrieves the current platform's default [PersistentStorage] instance.
 */
@ExperimentalPersistentStateAPI
@PublishedApi
internal expect fun PersistentStorage.Companion.platformDefault(): PersistentStorage

@ExperimentalPersistentStateAPI
@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
internal class SettingsPersistentStorage internal constructor(
    private val settings: Settings
) : PersistentStorage {

    private val mutex = Mutex(locked = false)

    override fun <T> get(
        key: String,
        defaultValue: T,
        serializersModule: SerializersModule,
        serializer: KSerializer<T>
    ): T = settings.decodeValue(
        key = key,
        defaultValue = defaultValue,
        serializersModule = serializersModule,
        serializer = serializer
    )

    override suspend fun <T> set(
        key: String,
        value: T,
        serializersModule: SerializersModule,
        serializer: KSerializer<T>
    ) {
        mutex.withLock {
            settings.encodeValue(
                key = key,
                value = value,
                serializersModule = serializersModule,
                serializer = serializer
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SettingsPersistentStorage) return false

        if (settings != other.settings) return false

        return mutex == other.mutex
    }

    override fun hashCode(): Int {
        var result = settings.hashCode()
        result = 31 * result + mutex.hashCode()
        return result
    }

    override fun toString(): String =
        "SettingsPersistentStorage(settings=$settings, mutex=$mutex)"
}
