package com.kodetools.statex.container

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MutableStateContainerOfTest {

    @Test
    fun testMutableStateContainerOfWithInitialValue() = runTest {
        // Create a test dispatcher
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)

        // Given
        val initialValue = "initial"
        
        // When
        val container = mutableStateContainerOf(
            initialStateValue = initialValue,
            flowCoroutineScope = backgroundScope,
            emitDispatcher = testDispatcher,
            flowDispatcher = testDispatcher
        )

        // Then
        assertTrue(container is DefaultMutableStateContainer, 
            "mutableStateContainerOf with initialStateValue should return a DefaultMutableStateContainer instance")
    }

    @Test
    fun testMutableStateContainerOfWithSnapshot() = runTest {
        // Create a test dispatcher
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)

        // Given
        val initialValue = "initial"
        val currentValue = "current"
        val snapshot = StateContainer.SnapshotStateModel(
            initialStateValue = initialValue,
            currentStateValue = currentValue
        )
        
        // When
        val container = mutableStateContainerOf(
            snapshot = snapshot,
            flowCoroutineScope = backgroundScope,
            emitDispatcher = testDispatcher,
            flowDispatcher = testDispatcher
        )

        // Then
        assertTrue(container is DefaultMutableStateContainer, 
            "mutableStateContainerOf with snapshot should return a DefaultMutableStateContainer instance")
    }
}
