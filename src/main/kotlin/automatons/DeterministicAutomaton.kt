package automatons

import events.Event
import states.State

/**
 * A deterministic automaton is an automaton that can't be in multiple states at a time.
 * @see Automaton
 */
interface DeterministicAutomaton : Automaton {
	/**
	 * Finds the state inserted in relation to a given start state and an event.
	 * Pattern : (Start State, Event) -> (End State)
	 * This is a shortcut method for derivate(), that outputs a state instead of a set of states.
	 * @param startState State to search with, as start state.
	 * @param event Event to search with.
	 * @return The state with a transition defined with the given start state and the given event, null if no such state exists.
	 * @see derivate
	 */
	fun derivateSingle(startState: State, event: Event): State?
}