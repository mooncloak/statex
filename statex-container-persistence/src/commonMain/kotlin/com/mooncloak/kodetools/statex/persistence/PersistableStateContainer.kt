package com.mooncloak.kodetools.statex.persistence

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.Stable
import androidx.compose.runtime.structuralEqualityPolicy
import com.mooncloak.kodetools.statex.MutableStateContainer
import com.mooncloak.kodetools.statex.StateContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

/**
 * A [MutableStateContainer] that persists its values to storage.
 */
@ExperimentalPersistentStateAPI
public interface PersistableStateContainer<T> : MutableStateContainer<T> {

    public companion object
}

/**
 * Creates a [PersistableStateContainer] with the provided values.
 *
 * @param [key] The key of the persisted value.
 *
 * @param [defaultValue] The default [StateContainer.initial] value and the starting
 * [StateContainer.current] value. Note that this value can change over time with a
 * [MutableStateContainer], if the [MutableStateContainer.reset] function is invoked. This value
 * will be used if there is no current persisted value.
 *
 * @param [serializersModule] The [SerializersModule] used to obtain a default [KSerializer] for
 * the value type if one is not provided. Defaults to [EmptySerializersModule].
 *
 * @param [serializer] The [KSerializer] used to encode and decode the value from the persistent
 * storage.
 *
 * @param [policy] The [SnapshotMutationPolicy] that is used to construct the underlying
 * [MutableState] instances.
 *
 * @param [dispatcher] The [MainCoroutineDispatcher] that is used to dispatch the changes to the state. Defaults to
 * [Dispatchers.Main]. This could be updated to [Dispatchers.Main] [MainCoroutineDispatcher.immediate] on supported
 * platforms.
 *
 * ## Example Usage
 *
 * ```kotlin
 * val stateContainer = mutableStateContainerOf(true)
 *
 * stateContainer.current.value // true
 * stateContainer.initial.value // true
 * stateContainer.changed.value // false
 *
 * // Mutate the value
 * stateContainer.change(value = false)
 *
 * stateContainer.current.value // false
 * stateContainer.initial.value // true
 * stateContainer.changed.value // true
 * ```
 */
@Stable
@ExperimentalPersistentStateAPI
public inline fun <reified T> persistableStateContainerOf(
    key: String,
    defaultValue: T,
    serializersModule: SerializersModule = EmptySerializersModule(),
    serializer: KSerializer<T> = serializersModule.serializer(),
    policy: SnapshotMutationPolicy<T> = structuralEqualityPolicy(),
    storage: PersistentStorage = PersistentStorage.platformDefault(),
    dispatcher: MainCoroutineDispatcher = Dispatchers.Main
): PersistableStateContainer<T> = DefaultPersistableStateContainer(
    key = key,
    defaultValue = defaultValue,
    serializer = serializer,
    serializersModule = serializersModule,
    policy = policy,
    storage = storage,
    dispatcher = dispatcher
)
