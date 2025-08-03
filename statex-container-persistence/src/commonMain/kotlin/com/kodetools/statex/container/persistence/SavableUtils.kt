package com.kodetools.statex.container.persistence

import com.kodetools.statex.container.StateContainer
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

@ExperimentalPersistentStateAPI
public fun <T> stateContainerSnapshotSaver(
    format: StringFormat,
    serializer: KSerializer<T>
): StringFormatSaver<StateContainer.SnapshotStateModel<T>> = StringFormatSaver(
    format = format,
    serializer = StateContainer.SnapshotStateModel.serializer(serializer)
)

@ExperimentalPersistentStateAPI
public fun <T> stateContainerSnapshotSaver(
    format: BinaryFormat,
    serializer: KSerializer<T>
): BinaryFormatByteArraySaver<StateContainer.SnapshotStateModel<T>> = BinaryFormatByteArraySaver(
    format = format,
    serializer = StateContainer.SnapshotStateModel.serializer(serializer)
)

@ExperimentalPersistentStateAPI
public fun <T> stateContainerSnapshotSaverAsBinaryHexString(
    format: BinaryFormat,
    serializer: KSerializer<T>
): BinaryFormatHexStringSaver<StateContainer.SnapshotStateModel<T>> = BinaryFormatHexStringSaver(
    format = format,
    serializer = StateContainer.SnapshotStateModel.serializer(serializer)
)
