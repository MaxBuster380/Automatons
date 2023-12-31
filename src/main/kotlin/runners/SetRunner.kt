package runners

import automatons.Automaton
import events.Event
import exceptions.NoStateException
import states.State
import states.TerminalState

/**
 * Implementation of Runner using a set.
 * @see Runner
 * @see Set
 */
class SetRunner(private val automaton : Automaton, startStates : Set<State>) : Runner {

	private var currentStates : Set<State>

	init {
		currentStates = startStates
	}

	// PUBLIC INSTANCE METHODS

	override fun apply(event : Event) {
		val newStates = calculateStatesDerivate(event)

		if (newStates.isEmpty()) {
			throw NoStateException("This runner has no more current states.")
		}

		currentStates = newStates
	}

	override fun canApply(event : Event) : Boolean {
		val newStates = calculateStatesDerivate(event)

		return newStates.isNotEmpty()
	}

	override fun isInState(state: State): Boolean {
		return getCurrentStates().contains(state)
	}

	override fun isOver() : Boolean {
		for(state in currentStates) {
			if (state is TerminalState) {
				return true
			}
		}
		return false
	}

	override fun getCurrentStates() : Set<State> {
		return currentStates
	}

	override fun getAutomaton() : Automaton {
		return automaton
	}

	// PRIVATE INSTANCE METHODS

	private fun calculateStatesDerivate(event : Event) : Set<State> {
		val res = mutableSetOf<State>()

		for(startState in currentStates) {
			res.addAll(automaton.derivate(startState, event))
		}

		return res
	}
}