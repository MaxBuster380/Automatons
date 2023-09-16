package automatons

import events.Event
import states.State

/**
 * Interface for a non-deterministic finite-state machine.
 *
 * @see [https://en.wikipedia.org/wiki/Finite-state_machine]
 */
interface Automaton {

	/**
	 * Adds a transition relationship between a start state, an event, and an end state.
	 * @param startState Start state of the new transition.
	 * @param event Event of the new transition.
	 * @param endState End state of the new transition.
	 * @return This instance of Automaton for successive calls.
	 */
	fun add(startState : State, event : Event, endState : State) : Automaton

	/**
	 * Finds all the states inserted in relation to a given start state and an event.
	 * @param startState State to search with, as start state.
	 * @param event Event to search with.
	 * @return A set of all states with a transition defined with the given start state and the given event.
	 */
	fun derivate(startState : State, event : Event) : Set<State>
}