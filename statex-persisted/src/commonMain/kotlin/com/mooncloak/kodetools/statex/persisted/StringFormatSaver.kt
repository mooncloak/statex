package com.mooncloak.kodetools.statex.persisted

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToHexString

public interface SerialFormatSaver<Original, Saveable : Any> : Saver<Original, Saveable> {

    public val format: SerialFormat

    public companion object
}

public class StringFormatSaver<Value> public constructor(
    override val format: StringFormat,
    private val serializer: KSerializer<Value>
) : SerialFormatSaver<Value, String> {

    override fun restore(value: String): Value? =
        format.decodeFromString(
            deserializer = serializer,
            string = value
        )

    override fun SaverScope.save(value: Value): String =
        format.encodeToString(
            serializer = serializer,
            value = value
        )
}

public class BinaryFormatByteArraySaver<Value> public constructor(
    override val format: BinaryFormat,
    private val serializer: KSerializer<Value>
) : SerialFormatSaver<Value, ByteArray> {

    override fun restore(value: ByteArray): Value? =
        format.decodeFromByteArray(
            deserializer = serializer,
            bytes = value
        )

    override fun SaverScope.save(value: Value): ByteArray =
        format.encodeToByteArray(
            serializer = serializer,
            value = value
        )
}

public class BinaryFormatHexStringSaver<Value> public constructor(
    override val format: BinaryFormat,
    private val serializer: KSerializer<Value>
) : SerialFormatSaver<Value, String> {

    override fun restore(value: String): Value? =
        format.decodeFromHexString(
            deserializer = serializer,
            hex = value
        )

    override fun SaverScope.save(value: Value): String =
        format.encodeToHexString(
            serializer = serializer,
            value = value
        )
}
