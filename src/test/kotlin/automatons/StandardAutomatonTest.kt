package automatons

import events.StandardEvent
import kotlin.test.Test
import states.StandardState
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StandardAutomatonTest {

	/**
	 * Makes sure the Automaton.add function returns the same instance.
	 */
	@Test
	fun addFunctionReturnsSelf() {
		val automaton = StandardAutomaton()

		val state = StandardState("1")

		val event = StandardEvent("a")

		val result = automaton.add(state, event, state)

		assertEquals(result, automaton)
	}

	/**
	 * Checks that Automaton.derivate's output is a set of only states which have a transition defined.
	 */
	@Test
	fun derivateOnlyOutputsReleventStates() {
		val automaton = StandardAutomaton()

		val state1 = StandardState("1")
		val state2 = StandardState("2")
		val state3 = StandardState("3")
		val state4 = StandardState("4")

		val eventA = StandardEvent("a")
		val eventB = StandardEvent("b")

		automaton
			.add(state1, eventA, state1)
			.add(state1, eventA, state2)
			.add(state1, eventB, state3)
			.add(state2, eventA, state4)

		val outputSet = automaton.derivate(state1, eventA)

		val containsState1 = outputSet.contains(state1)
		val containsState2 = outputSet.contains(state2)
		val containsState3 = outputSet.contains(state3)
		val containsState4 = outputSet.contains(state4)

		assertTrue(containsState1 && containsState2 && !containsState3 && !containsState4)
	}

	/**
	 * Checks if Automaton.getEventsFrom only gives events with a defined transition.
	 */
	@Test
	fun getEventsFrom() {
		val automaton = StandardAutomaton()

		val state1 = StandardState("1")
		val state2 = StandardState("2")
		val state3 = StandardState("3")
		val state4 = StandardState("4")
		val state5 = StandardState("5")

		val eventA = StandardEvent("a")
		val eventB = StandardEvent("b")
		val eventC = StandardEvent("c")
		val eventD = StandardEvent("d")

		automaton
			.add(state1, eventA, state1)
			.add(state1, eventA, state2)
			.add(state1, eventB, state3)
			.add(state2, eventC, state4)
			.add(state4, eventD, state5)

		val outputSet = automaton.getEventsFrom(state1)

		val containsEventA = outputSet.contains(eventA)
		val containsEventB = outputSet.contains(eventB)
		val containsEventC = outputSet.contains(eventC)
		val containsEventD = outputSet.contains(eventD)

		assertTrue(containsEventA && containsEventB && !containsEventC && !containsEventD)
	}

	/**
	 * Checks if Automaton.getEventsTo only gives events with a defined transition.
	 */
	@Test
	fun getEventsTo() {
		val automaton = StandardAutomaton()

		val state1 = StandardState("1")
		val state2 = StandardState("2")
		val state3 = StandardState("3")
		val state4 = StandardState("4")
		val state5 = StandardState("5")

		val eventA = StandardEvent("a")
		val eventB = StandardEvent("b")
		val eventC = StandardEvent("c")
		val eventD = StandardEvent("d")

		automaton
			.add(state1, eventA, state1)
			.add(state2, eventA, state2)
			.add(state3, eventB, state1)
			.add(state4, eventC, state4)
			.add(state5, eventD, state1)

		val outputSet = automaton.getEventsTo(state1)

		val containsEventA = outputSet.contains(eventA)
		val containsEventB = outputSet.contains(eventB)
		val containsEventC = outputSet.contains(eventC)
		val containsEventD = outputSet.contains(eventD)

		assertTrue(containsEventA && containsEventB && !containsEventC && containsEventD)
	}

	/**
	 * Checks that Automaton.integrate's output is a set of only states which have a transition defined.
	 */
	@Test
	fun integrateOnlyOutputsReleventStates() {
		val automaton = StandardAutomaton()

		val state1 = StandardState("1")
		val state2 = StandardState("2")
		val state3 = StandardState("3")
		val state4 = StandardState("4")

		val eventA = StandardEvent("a")
		val eventB = StandardEvent("b")

		automaton
			.add(state1, eventA, state1)
			.add(state1, eventA, state2)
			.add(state1, eventB, state3)
			.add(state2, eventA, state4)

		val outputSet = automaton.integrate(eventA, state4)

		val containsState1 = outputSet.contains(state1)
		val containsState2 = outputSet.contains(state2)
		val containsState3 = outputSet.contains(state3)
		val containsState4 = outputSet.contains(state4)

		assertTrue(!containsState1 && containsState2 && !containsState3 && !containsState4)
	}

	/**
	 * Checks that Automaton.getEventsBetween only gives the relevent events.
	 */
	@Test
	fun eventsBetweenOnlyGiveReleventEvents() {
		val automaton = StandardAutomaton()

		val state1 = StandardState("1")
		val state2 = StandardState("2")
		val state3 = StandardState("3")

		val eventA = StandardEvent("a")
		val eventB = StandardEvent("b")
		val eventC = StandardEvent("c")

		automaton
			.add(state1, eventA, state2)
			.add(state1, eventB, state2)
			.add(state1, eventC, state3)
			.add(state3, eventC, state2)

		val outputSet = automaton.getEventsBetween(state1, state2)

		val containsEventA = outputSet.contains(eventA)
		val containsEventB = outputSet.contains(eventB)
		val containsEventC = outputSet.contains(eventC)

		assertTrue(containsEventA && containsEventB && !containsEventC)
	}
}