package com.kodetools.statex.container

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TurbineDefaultMutableStateContainerTest {

    @Test
    fun testInitialAndCurrentValues() = runTest {
        // Create a test dispatcher
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)

        // Given
        val initialValue = "initial"
        val currentValue = "current"
        val container = DefaultMutableStateContainer(
            initialStateValue = initialValue,
            currentStateValue = currentValue,
            flowCoroutineScope = backgroundScope, // Use backgroundScope to ensure proper cancellation
            emitDispatcher = testDispatcher,
            flowDispatcher = testDispatcher
        )

        // Then
        assertEquals(initialValue, container.initial.value)

        // Test current flow emissions
        backgroundScope.run {
            container.current.test {
                // Verify initial state
                assertEquals(initialValue, awaitItem())

                // Then - verify current value is updated
                assertEquals(currentValue, awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun testCurrentValueDifferentFromInitial() = runTest {
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

        // Test current flow emissions
        backgroundScope.run {
            container.current.test {
                // Verify initial state
                assertEquals(initialValue, awaitItem())

                // When - update the current value
                val currentValue = "current"
                container.update { currentValue }

                // Then - verify current value is updated
                assertEquals(currentValue, awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
        }

        // Verify initial value remains unchanged
        assertEquals(initialValue, container.initial.value)
    }

    @Test
    fun testUpdateFunction() = runTest {
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

        // Test current flow emissions
        backgroundScope.run {
            container.current.test {
                // Verify initial state
                assertEquals(initialValue, awaitItem(), "Current value should start with initial value")

                // When - update the current value
                val newValue = "updated"
                container.update { newValue }

                // Then - verify current value is updated
                assertEquals(newValue, awaitItem(), "Current value should be updated")

                cancelAndIgnoreRemainingEvents()
            }
        }

        // Verify initial value remains unchanged
        assertEquals(initialValue, container.initial.value, "Initial value should remain unchanged")
    }

    @Test
    fun testUpdateFunctionWithCurrentValue() = runTest {
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

        // Test current flow emissions
        backgroundScope.run {
            container.current.test {
                // Verify initial state
                assertEquals(initialValue, awaitItem(), "Current value should start with initial value")

                // When - update the current value with transformation
                container.update { current -> current + "-updated" }

                // Then - verify current value is updated with transformation
                assertEquals(
                    "$initialValue-updated",
                    awaitItem(),
                    "Current value should be updated with transformation"
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

        // Verify initial value remains unchanged
        assertEquals(initialValue, container.initial.value, "Initial value should remain unchanged")
    }

    @Test
    fun testResetFunction() = runTest {
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

        // Test current flow emissions
        backgroundScope.run {
            container.current.test {
                // Verify initial state
                assertEquals(initialValue, awaitItem(), "Current value should start with initial value")

                // When - update the current value
                val updatedValue = "updated"
                container.update { updatedValue }

                // Verify the update worked
                assertEquals(updatedValue, awaitItem(), "Current value should be updated")

                // When - reset to initial value
                container.reset()

                // Then - verify current value is reset to initial value
                assertEquals(initialValue, awaitItem(), "Current value should be reset to initial value")

                cancelAndIgnoreRemainingEvents()
            }
        }

        // Verify initial value remains unchanged
        assertEquals(initialValue, container.initial.value, "Initial value should remain unchanged after reset")
    }

    @Test
    fun testResetFunctionWithNewInitialValue() = runTest {
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

        // Test initial and current flow emissions
        backgroundScope.run {
            // Test initial flow
            container.initial.test {
                // Verify initial state
                assertEquals(initialValue, awaitItem(), "Initial value should start with initialValue")

                // When - reset with new initial value
                val newInitialValue = "new-initial"
                container.reset { newInitialValue }

                // Then - verify initial value is updated
                assertEquals(newInitialValue, awaitItem(), "Initial value should be updated to new value")

                cancelAndIgnoreRemainingEvents()
            }
        }

        backgroundScope.run {
            // Test current flow
            container.current.test {
                // Skip initial value
                awaitItem()

                // When - reset with new initial value
                val newInitialValue = "new-initial"
                container.reset { newInitialValue }

                // Then - verify current value is updated to new initial value
                assertEquals(newInitialValue, awaitItem(), "Current value should be updated to new value")

                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun testSnapshotFunction() = runTest {
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

        // Test current flow emissions
        backgroundScope.run {
            container.current.test {
                // Verify initial state
                assertEquals(initialValue, awaitItem(), "Current value should start with initial value")

                // Take a snapshot of the initial state
                val initialSnapshot = container.snapshot()
                assertEquals(
                    initialValue,
                    initialSnapshot.initialStateValue,
                    "Initial snapshot initialStateValue should match"
                )
                assertEquals(
                    initialValue,
                    initialSnapshot.currentStateValue,
                    "Initial snapshot currentStateValue should match"
                )

                // When - update the current value
                val updatedValue = "updated"
                container.update { updatedValue }

                // Verify the update worked
                assertEquals(updatedValue, awaitItem(), "Current value should be updated")

                // Take a snapshot after the update
                val updatedSnapshot = container.snapshot()

                // Then - verify the snapshot contains the correct values
                assertEquals(
                    initialValue,
                    updatedSnapshot.initialStateValue,
                    "Updated snapshot initialStateValue should remain unchanged"
                )
                assertEquals(
                    updatedValue,
                    updatedSnapshot.currentStateValue,
                    "Updated snapshot currentStateValue should be updated"
                )

                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun testFlowEmissions() = runTest {
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

        // Test initial flow emissions
        backgroundScope.run {
            container.initial.test {
                assertEquals(initialValue, awaitItem())

                // Update initial value
                container.reset { "new-initial" }
                assertEquals("new-initial", awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
        }

        // Test current flow emissions
        backgroundScope.run {
            container.current.test {
                assertEquals(initialValue, awaitItem())

                // Update current value
                container.update { "updated" }
                assertEquals("updated", awaitItem())

                // Reset to initial value
                container.reset { "new-initial" }
                assertEquals("new-initial", awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
