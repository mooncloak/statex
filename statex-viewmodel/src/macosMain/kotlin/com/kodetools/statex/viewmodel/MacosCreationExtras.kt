package com.kodetools.statex.viewmodel

/**
 * Key for the elements of [CreationExtras]. [T] represents the type of element associated with
 * this key.
 */
public actual interface CreationExtrasKey<T>

internal actual object CreationExtrasEmpty : CreationExtras() {
    actual override fun <T> get(key: CreationExtrasKey<T>): T? = null
}

@PublishedApi
internal actual inline fun <reified T> newCreationExtrasKey(): CreationExtrasKey<T> = object : CreationExtrasKey<T> {}

/**
 * A map-like object holding pairs of [CreationExtras.Key] and [Any], enabling efficient value
 * retrieval for each key. Each key in [CreationExtras] is unique, storing only one value per key.
 *
 * [CreationExtras] is used in [ViewModelProvider.Factory.create] to provide extra information to
 * the [Factory]. This makes [Factory] implementations stateless, simplifying factory injection by
 * not requiring all information at construction time.
 *
 * This abstract class supports read-only access; use [MutableCreationExtras] for read-write access.
 */
public actual abstract class CreationExtras internal constructor() {

    internal val extras: MutableMap<CreationExtrasKey<*>, Any?> = mutableMapOf()

    /**
     * Returns the value to which the specified [key] is associated, or null if this
     * [CreationExtras] contains no mapping for the key.
     */
    public actual abstract operator fun <T> get(key: CreationExtrasKey<T>): T?

    /** Compares the specified object with this [CreationExtras] for equality. */
    actual override fun equals(other: Any?): Boolean = other is CreationExtras && extras == other.extras

    /** Returns the hash code value for this [CreationExtras]. */
    actual override fun hashCode(): Int = extras.hashCode()

    /**
     * Returns a string representation of this [CreationExtras]. The string representation consists
     * of a list of key-value mappings in the order returned by the [CreationExtras]'s iterator.
     */
    actual override fun toString(): String = "CreationExtras(extras=$extras)"

    public actual companion object
}

/**
 * A modifiable [CreationExtras] that holds pairs of [CreationExtras.Key] and [Any], allowing
 * efficient value retrieval for each key.
 *
 * Each key in [CreationExtras] is unique, storing only one value per key.
 *
 * @see [CreationExtras]
 */
public actual class MutableCreationExtras
/**
 * Constructs a [MutableCreationExtras] containing the elements of the specified `initialExtras`, in
 * the order they are returned by the [Map]'s iterator.
 */
internal constructor(initialExtras: Map<CreationExtrasKey<*>, Any?>) : CreationExtras() {

    /**
     * Constructs a [MutableCreationExtras] containing the elements of the specified
     * [initialExtras], in the order they are returned by the [CreationExtras]'s iterator.
     */
    public constructor(initialExtras: CreationExtras = CreationExtrasEmpty) : this(initialExtras.extras)

    init {
        extras += initialExtras
    }

    /** Associates the specified [t] with the specified [key] in this [CreationExtras]. */
    public actual operator fun <T> set(key: CreationExtrasKey<T>, t: T) {
        extras[key] = t
    }

    /**
     * Returns the value to which the specified [key] is associated, or null if this
     * [CreationExtras] contains no mapping for the key.
     */
    @Suppress("UNCHECKED_CAST")
    public actual override fun <T> get(key: CreationExtrasKey<T>): T? = extras[key] as T?
}

public actual fun createMutableCreationExtras(initialExtras: CreationExtras): MutableCreationExtras =
    MutableCreationExtras(initialExtras)

public actual operator fun CreationExtras.contains(key: CreationExtrasKey<*>): Boolean = key in extras

public actual operator fun CreationExtras.plus(creationExtras: CreationExtras): MutableCreationExtras =
    MutableCreationExtras(initialExtras = extras + creationExtras.extras)

public actual operator fun MutableCreationExtras.plusAssign(creationExtras: CreationExtras) {
    extras += creationExtras.extras
}
