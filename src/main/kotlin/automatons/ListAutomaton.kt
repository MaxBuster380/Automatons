package automatons

import events.Event
import states.State

/**
 * Implementation of Automaton using an unordered list.
 * @see Automaton
 * @see List
 */
class ListAutomaton : Automaton {
	private data class Transition(val startState : State, val event : Event, val endState : State)
	private val transitions = mutableListOf<Transition>()

	// PUBLIC INSTANCE METHODS

	override fun add(startState : State, event : Event, endState : State) : Automaton {
		val newTransition = Transition(startState, event, endState)

		if (!isRedundant(newTransition)) {
			transitions += newTransition
		}

		return this
	}

	override fun derivate(startState : State, event : Event) : Set<State> {
		val res = mutableSetOf<State>()

		for (currentTransition in transitions) {
			if (currentTransition.startState == startState && currentTransition.event == event) {
				res.add(currentTransition.endState)
			}
		}

		return res
	}

	override fun getStates(): Set<State> {
		val startStates = transitions.map { it.startState }.toSet()
		val endStates = transitions.map { it.endState }.toSet()

		return startStates + endStates
	}

	override fun getEvents(): Set<Event> {
		return transitions.map { it.event }.toSet()
	}

	// PRIVATE INSTANCE METHODS

	/**
	 * Checks if the transition has already been added.
	 */
	private fun isRedundant(newTransition : Transition) : Boolean {
		for(currentTransition in transitions) {
			if (currentTransition == newTransition) {
				return true
			}
		}
		return false
	}
}