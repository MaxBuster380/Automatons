package automatons.deterministicautomatons

import automatons.Automaton
import automatons.ListAutomaton
import events.Event
import exceptions.DuplicateTransitionException
import exceptions.RedundantEncapsulation
import states.State

/**
 * Adds deterministic logic to a given instance of Automaton.
 * @see DeterministicAutomaton
 */
class HighLevelDeterministicAutomaton(
	private val subAutomaton : Automaton
) : DeterministicAutomaton {
	constructor() : this(ListAutomaton())

	init {
		if (subAutomaton is DeterministicAutomaton) {
			throw RedundantEncapsulation("The sub-automaton is already a DeterministicAutomaton.")
		}
	}

	// PUBLIC INSTANCE METHODS

	// PUBLIC INSTANCE METHODS - INTERFACE DeterministicAutomaton

	override fun derivateSingle(startState: State, event: Event): State? {
		return try {
			subAutomaton.derivate(startState, event).first()
		}catch(_:Exception) {
			null
		}
	}

	// PUBLIC INSTANCE METHODS - INTERFACE Automaton

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

	override fun getStates(): Set<State> {
		return subAutomaton.getStates()
	}

	override fun getEvents(): Set<Event> {
		return subAutomaton.getEvents()
	}
}