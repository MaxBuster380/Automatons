package automatons

import events.Event
import states.State

interface Automaton {
	/**
	 * Adds a transition relationship between a start state, an event, and an end state.
	 * @param startState
	 * @param event
	 * @param endState
	 * @return This instance of Automaton for successive calls.
	 */
	fun add(startState : State, event : Event, endState : State) : Automaton

	/**
	 * Finds all the states inserted in relation to a given start state and an event.
	 * Pattern : (Start State, Event) -> (End State)
	 * @param startState State to derivate from.
	 * @param event Event to derivate on.
	 * @return A set of all states with a transition defined with the given start state and the given event.
	 */
	fun derivate(startState : State, event : Event) : Set<State>

	/**
	 * Returns all the events with a defined transition from a given state.
	 * Pattern : (Start State) -> (Event)
	 * @param startState State to get outgoing events from.
	 * @return A set of events that, derivating from the given state, does not give an empty set.
	 * @see derivate
	 */
	fun getEventsFrom(startState : State) : Set<Event>

	/**
	 * Returns all the events with a defined transition to a given state.
	 * Pattern : (End State) -> (Event)
	 * @param endState State to get outgoing events to.
	 * @return A set of events that have a transition defined where the given state is the end state.
	 * @see derivate
	 */
	fun getEventsTo(endState : State) : Set<Event>

	/**
	 * Finds all the states inserted in relation to a given event and end state.
	 * Pattern : (Event, End State) -> (Start State)
	 * @param event Event to integrate on.
	 * @param endState State to integrate from.
	 * @return A set of all states with a transition defined with the given event and the given end state.
	 */
	fun integrate(event : Event, endState : State) : Set<State>

	/**
	 * Finds all the events that, when derivated from the given start state, gives the given end state.
	 * Pattern : (Start State, End State) -> (Event)
	 * @param startState
	 * @param endState
	 * @return A set of events that have a transition from the given start state and to the given end state.
	 * @see derivate
	 */
	fun getEventsBetween(startState : State, endState : State) : Set<Event>

	/**
	 * Finds all the states that can be derivated from a given start state.
	 * Pattern : (Start State) -> (End State)
	 * @param startState State derivating from.
	 * @return A set of states that have a transition defined with the given state as start state and it as end state.
	 * @see derivate
	 */
	fun getEndStatesFrom(startState : State) : Set<State>

	/**
	 * Finds all the states that can be integrated from a given start state.
	 * Pattern : (End State) -> (Start State)
	 * @param endState State integrating from.
	 * @return A set of states that have a transition defined with the given state as end state and it as start state.
	 * @see integrate
	 */
	fun getStartStatesFrom(endState : State) : Set<State>

	/**
	 * Finds all the states that can be derivated to by a given event.
	 * Pattern : (Event) -> (End State)
	 * @param event Event derivating on.
	 * @return A set of states that have a transition defined with the given event as event and it as end state.
	 * @see derivate
	 */
	fun getEventDestinations(event : Event) : Set<State>

	/**
	 * Finds all the states that can be integrated to by a given event.
	 * Pattern : (Event) -> (Start State)
	 * @param event Event integrating on.
	 * @return A set of states that have a transition defined with the given event as event and it as start state.
	 * @see integrate
	 */
	fun getEventOrigins(event : Event) : Set<State>
}