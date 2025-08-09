package com.kodetools.statex.container

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class StateContainerSnapshotStateModelTest {

    @Test
    fun testSnapshotStateModelCreation() {
        // Given
        val initialValue = "initial"
        val currentValue = "current"

        // When
        val snapshot = StateContainer.SnapshotStateModel(
            initialStateValue = initialValue,
            currentStateValue = currentValue
        )

        // Then
        assertEquals(initialValue, snapshot.initialStateValue, "Initial state value should match")
        assertEquals(currentValue, snapshot.currentStateValue, "Current state value should match")
    }

    @Test
    fun testSnapshotStateModelEquality() {
        // Given
        val snapshot1 = StateContainer.SnapshotStateModel(
            initialStateValue = "initial",
            currentStateValue = "current"
        )

        val snapshot2 = StateContainer.SnapshotStateModel(
            initialStateValue = "initial",
            currentStateValue = "current"
        )

        val snapshot3 = StateContainer.SnapshotStateModel(
            initialStateValue = "different",
            currentStateValue = "current"
        )

        val snapshot4 = StateContainer.SnapshotStateModel(
            initialStateValue = "initial",
            currentStateValue = "different"
        )

        // Then
        assertEquals(snapshot1, snapshot2, "Identical snapshots should be equal")
        assertNotEquals(snapshot1, snapshot3, "Snapshots with different initialStateValue should not be equal")
        assertNotEquals(snapshot1, snapshot4, "Snapshots with different currentStateValue should not be equal")
    }

    @Test
    fun testSnapshotStateModelCopy() {
        // Given
        val initialValue = "initial"
        val currentValue = "current"
        val snapshot = StateContainer.SnapshotStateModel(
            initialStateValue = initialValue,
            currentStateValue = currentValue
        )

        // When
        val copiedSnapshot = snapshot.copy()
        val modifiedInitialSnapshot = snapshot.copy(initialStateValue = "new-initial")
        val modifiedCurrentSnapshot = snapshot.copy(currentStateValue = "new-current")
        val modifiedBothSnapshot = snapshot.copy(
            initialStateValue = "new-initial",
            currentStateValue = "new-current"
        )

        // Then
        assertEquals(snapshot, copiedSnapshot, "Copied snapshot should equal original")
        assertEquals(initialValue, copiedSnapshot.initialStateValue, "Copied snapshot should have same initialStateValue")
        assertEquals(currentValue, copiedSnapshot.currentStateValue, "Copied snapshot should have same currentStateValue")

        assertEquals("new-initial", modifiedInitialSnapshot.initialStateValue, "Modified initial snapshot should have updated initialStateValue")
        assertEquals(currentValue, modifiedInitialSnapshot.currentStateValue, "Modified initial snapshot should keep original currentStateValue")

        assertEquals(initialValue, modifiedCurrentSnapshot.initialStateValue, "Modified current snapshot should keep original initialStateValue")
        assertEquals("new-current", modifiedCurrentSnapshot.currentStateValue, "Modified current snapshot should have updated currentStateValue")

        assertEquals("new-initial", modifiedBothSnapshot.initialStateValue, "Modified both snapshot should have updated initialStateValue")
        assertEquals("new-current", modifiedBothSnapshot.currentStateValue, "Modified both snapshot should have updated currentStateValue")
    }

    @Test
    fun testSnapshotStateModelToString() {
        // Given
        val initialValue = "initial"
        val currentValue = "current"
        val snapshot = StateContainer.SnapshotStateModel(
            initialStateValue = initialValue,
            currentStateValue = currentValue
        )

        // When
        val stringRepresentation = snapshot.toString()

        // Then
        // Verify the toString() output contains both property values
        assertTrue(stringRepresentation.contains(initialValue), "toString() should contain initialStateValue")
        assertTrue(stringRepresentation.contains(currentValue), "toString() should contain currentStateValue")
    }

    @Test
    fun testSnapshotStateModelWithComplexTypes() {
        // Given
        data class ComplexType(val id: Int, val name: String)

        val initialComplex = ComplexType(1, "initial")
        val currentComplex = ComplexType(2, "current")

        // When
        val snapshot = StateContainer.SnapshotStateModel(
            initialStateValue = initialComplex,
            currentStateValue = currentComplex
        )

        // Then
        assertEquals(initialComplex, snapshot.initialStateValue, "Complex initial state value should match")
        assertEquals(currentComplex, snapshot.currentStateValue, "Complex current state value should match")
    }
}
