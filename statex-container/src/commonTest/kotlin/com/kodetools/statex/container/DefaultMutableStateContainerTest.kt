package com.kodetools.statex.container

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultMutableStateContainerTest {

    @Test
    fun testInitialValue() = runTest {
        // Create a test dispatcher
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)

        // Given
        val initialValue = "initial"
        val container = DefaultMutableStateContainer(
            initialStateValue = initialValue,
            flowCoroutineScope = backgroundScope, // Use backgroundScope to ensure proper cancellation
            emitDispatcher = testDispatcher,
            flowDispatcher = testDispatcher
        )

        // Then
        assertEquals(initialValue, container.initial.value)
        assertEquals(initialValue, container.current.value)
    }
}
