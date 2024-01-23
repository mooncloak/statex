package com.mooncloak.kodetools.statex

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.StateFactoryMarker
import kotlin.jvm.JvmInline

/**
 * Creates an immutable [State] instance wrapping the provided [value].
 */
@StateFactoryMarker
fun <T> stateOf(value: T): State<T> =
    ImmutableState(value = value)

/**
 * A [State] implementation that does not change its [value] over time.
 */
@Stable
@JvmInline
internal value class ImmutableState<T> internal constructor(
    override val value: T
) : State<T>
