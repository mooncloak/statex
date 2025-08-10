package com.kodetools.statex.container.persistence

import com.kodetools.statex.container.StateContainer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer as kSerializer

@Serializable
private data class StateContainerSnapshotStateModelDelegate<T>(
    @SerialName(value = "initial") val initialStateValue: T,
    @SerialName(value = "current") val currentStateValue: T,
)

public fun <T> StateContainer.SnapshotStateModel.Companion.serializer(
    valueSerializer: KSerializer<T>
): KSerializer<StateContainer.SnapshotStateModel<T>> =
    StateContainerSnapshotStateModelSerializer(
        valueSerializer = valueSerializer
    )

public inline fun <reified T> StateContainer.SnapshotStateModel.Companion.serializer(): KSerializer<StateContainer.SnapshotStateModel<T>> =
    StateContainerSnapshotStateModelSerializer(
        valueSerializer = kSerializer()
    )

public class StateContainerSnapshotStateModelSerializer<T> public constructor(
    valueSerializer: KSerializer<T>
) : KSerializer<StateContainer.SnapshotStateModel<T>> {

    private val delegateSerializer = StateContainerSnapshotStateModelDelegate.serializer(valueSerializer)

    override val descriptor: SerialDescriptor
        get() = delegateSerializer.descriptor

    override fun serialize(
        encoder: Encoder,
        value: StateContainer.SnapshotStateModel<T>
    ) {
        val delegate = StateContainerSnapshotStateModelDelegate(
            initialStateValue = value.initialStateValue,
            currentStateValue = value.currentStateValue
        )

        delegateSerializer.serialize(encoder, delegate)
    }

    override fun deserialize(decoder: Decoder): StateContainer.SnapshotStateModel<T> {
        val delegate = delegateSerializer.deserialize(decoder)

        return StateContainer.SnapshotStateModel(
            initialStateValue = delegate.initialStateValue,
            currentStateValue = delegate.currentStateValue
        )
    }
}
