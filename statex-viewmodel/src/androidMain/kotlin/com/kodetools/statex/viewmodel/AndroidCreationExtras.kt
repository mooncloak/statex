package com.kodetools.statex.viewmodel

import androidx.lifecycle.viewmodel.contains as platformContains
import androidx.lifecycle.viewmodel.plus as platformPlus
import androidx.lifecycle.viewmodel.plusAssign as platformPlusAssign

public actual typealias CreationExtras = androidx.lifecycle.viewmodel.CreationExtras
public actual typealias CreationExtrasKey<T> = androidx.lifecycle.viewmodel.CreationExtras.Key<T>
public actual typealias CreationExtrasEmpty = androidx.lifecycle.viewmodel.CreationExtras.Empty

@PublishedApi
internal actual inline fun <reified T> newCreationExtrasKey(): CreationExtrasKey<T> =
    androidx.lifecycle.viewmodel.CreationExtras.Key<T>()

public actual typealias MutableCreationExtras = androidx.lifecycle.viewmodel.MutableCreationExtras

@Suppress("NOTHING_TO_INLINE")
public actual inline fun createMutableCreationExtras(initialExtras: CreationExtras): MutableCreationExtras =
    androidx.lifecycle.viewmodel.MutableCreationExtras(initialExtras)

@Suppress("NOTHING_TO_INLINE")
public actual inline operator fun CreationExtras.contains(key: CreationExtrasKey<*>): Boolean =
    this.platformContains(key)

@Suppress("NOTHING_TO_INLINE")
public actual inline operator fun CreationExtras.plus(creationExtras: CreationExtras): MutableCreationExtras =
    this.platformPlus(creationExtras)

@Suppress("NOTHING_TO_INLINE")
public actual inline operator fun MutableCreationExtras.plusAssign(creationExtras: CreationExtras) =
    this.platformPlusAssign(creationExtras)
