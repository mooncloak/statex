package com.kodetools.statex.viewmodel

/**
 * Key for the elements of [CreationExtras]. [T] represents the type of element associated with
 * this key.
 */
public expect interface CreationExtrasKey<T>

/**
 * An empty read-only [CreationExtras].
 */
internal expect object CreationExtrasEmpty : CreationExtras {
    override fun <T> get(key: CreationExtrasKey<T>): T?
}

public val CreationExtras.Companion.Empty: CreationExtras
    get() = CreationExtrasEmpty

@PublishedApi
internal expect inline fun <reified T> newCreationExtrasKey(): CreationExtrasKey<T>

/**
 * Returns an unique [Key] to be associated with an extra.
 */
@Suppress("FunctionName")
public inline fun <reified T> CreationExtras.Companion.Key(): CreationExtrasKey<T> =
    newCreationExtrasKey()

/**
 * A map-like object holding pairs of [CreationExtrasKey] and [Any], enabling efficient value
 * retrieval for each key. Each key in [CreationExtras] is unique, storing only one value per key.
 *
 * [CreationExtras] is used in [ViewModelProvider.Factory.create] to provide extra information to
 * the [Factory]. This makes [Factory] implementations stateless, simplifying factory injection by
 * not requiring all information at construction time.
 *
 * This abstract class supports read-only access; use [MutableCreationExtras] for read-write access.
 */
public expect abstract class CreationExtras {

    /**
     * Returns the value to which the specified [key] is associated, or null if this
     * [CreationExtras] contains no mapping for the key.
     */
    public abstract operator fun <T> get(key: CreationExtrasKey<T>): T?

    /**
     * Compares the specified object with this [CreationExtras] for equality.
     */
    override fun equals(other: Any?): Boolean

    /**
     * Returns the hash code value for this [CreationExtras].
     */
    override fun hashCode(): Int

    /**
     * Returns a string representation of this [CreationExtras]. The string representation consists
     * of a list of key-value mappings in the order returned by the [CreationExtras]'s iterator.
     */
    override fun toString(): String

    public companion object
}

/**
 * A modifiable [CreationExtras] that holds pairs of [CreationExtrasKey] and [Any], allowing
 * efficient value retrieval for each key.
 *
 * Each key in [CreationExtras] is unique, storing only one value per key.
 *
 * @see [CreationExtras]
 */
public expect class MutableCreationExtras : CreationExtras {

    /**
     * Associates the specified [t] with the specified [key] in this [CreationExtras].
     */
    public operator fun <T> set(key: CreationExtrasKey<T>, t: T)

    override fun <T> get(key: CreationExtrasKey<T>): T?
}

/**
 * Constructs a [MutableCreationExtras] containing the elements of the specified
 * [initialExtras], in the order they are returned by the [CreationExtras]'s iterator.
 */
public expect fun createMutableCreationExtras(initialExtras: CreationExtras = CreationExtrasEmpty): MutableCreationExtras

/**
 * Checks if the [CreationExtras] contains the given [key].
 *
 * This method allows to use the `key in creationExtras` syntax for checking whether an [key] is
 * contained in the [CreationExtras].
 */
@Suppress("NOTHING_TO_INLINE")
public expect operator fun CreationExtras.contains(key: CreationExtrasKey<*>): Boolean

/**
 * Creates a new read-only [CreationExtras] by replacing or adding entries to [this] extras from
 * another [creationExtras].
 *
 * The returned [CreationExtras] preserves the entry iteration order of the original
 * [CreationExtras].
 *
 * Those entries of another [creationExtras] that are missing in [this] extras are iterated in the
 * end in the order of that [creationExtras].
 */
public expect operator fun CreationExtras.plus(creationExtras: CreationExtras): MutableCreationExtras

/**
 * Appends or replaces all entries from the given [creationExtras] in [this] mutable extras.
 */
public expect operator fun MutableCreationExtras.plusAssign(creationExtras: CreationExtras)
