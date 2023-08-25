package automatons.highlevelautomatons

import automatons.Automaton
import automatons.StandardAutomaton
import events.Event
import states.State

/**
 * A deterministic automaton is an automaton that can't be in multiple states at a time.
 * This implementation will disable having multiple transitions with equal start state and event.
 */
class DeterministicAutomaton(
	private val subAutomaton : Automaton
) : Automaton {
	constructor() : this(StandardAutomaton())

	// PUBLIC INSTANCE METHODS

	/**
	 * Finds the state inserted in relation to a given start state and an event.
	 * Pattern : (Start State, Event) -> (End State)
	 * This is a shortcut method for derivate(), that outputs a state instead of a set of states.
	 * @param startState State to search with, as start state.
	 * @param event Event to search with.
	 * @return The state with a transition defined with the given start state and the given event, null if no such state exists.
	 * @see derivate
	 */
	fun derivateSingle(startState: State, event: Event): State? {
		return try {
			subAutomaton.derivate(startState, event).first()
		}catch(_:Exception) {
			null
		}
	}

	// PUBLIC INSTANCE METHODS - INTERFACE Automaton

	/**
	 * Adds a transition relationship between a start state, an event, and an end state.
	 * @param startState Start state of the new transition.
	 * @param event Event of the new transition.
	 * @param endState End state of the new transition.
	 * @return This instance of Automaton for successive calls.
	 * @throws DuplicateTransitionException when (startState, event) is already defined.
	 */
	override fun add(startState: State, event: Event, endState: State): Automaton {
		val transitionAlreadyDefined = subAutomaton.derivate(startState, event).isNotEmpty()
		if (!transitionAlreadyDefined) {
			throw DuplicateTransitionException("(${startState}, ${event}) is already defined.")
		}

		subAutomaton.add(startState, event, endState)

		return this
	}

	override fun derivate(startState: State, event: Event): Set<State> {
		return subAutomaton.derivate(startState, event)
	}

	override fun getEventsFrom(startState: State): Set<Event> {
		return subAutomaton.getEventsFrom(startState)
	}

	override fun getEventsTo(endState: State): Set<Event> {
		return subAutomaton.getEventsTo(endState)
	}

	override fun integrate(event: Event, endState: State): Set<State> {
		return subAutomaton.integrate(event, endState)
	}

	override fun getEventsBetween(startState: State, endState: State): Set<Event> {
		return subAutomaton.getEventsBetween(startState, endState)
	}

	override fun getEndStatesFrom(startState: State): Set<State> {
		return subAutomaton.getEndStatesFrom(startState)
	}

	override fun getStartStatesFrom(endState: State): Set<State> {
		return subAutomaton.getStartStatesFrom(endState)
	}

	override fun getEventDestinations(event: Event): Set<State> {
		return subAutomaton.getEventDestinations(event)
	}

	override fun getEventOrigins(event: Event): Set<State> {
		return subAutomaton.getEventOrigins(event)
	}
}