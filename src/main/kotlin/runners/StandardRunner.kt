package runners

import automatons.Automaton
import events.Event
import states.State

class StandardRunner(private val automaton : Automaton, startStates : Set<State>) {

	private var currentStates : Set<State>

	init {
		currentStates = startStates
	}

	/**
	 * Changes the current states by derivating with an event.
	 * @param event Event to derivate with.
	 * @throws NoStateException when the runner has no current states after derivating.
	 */
	fun apply(event : Event) {
		val newStates = mutableSetOf<State>()

		for(startState in currentStates) {
			newStates.addAll(automaton.derivate(startState, event))
		}

		if (newStates.isEmpty()) {
			throw NoStateException("This runner has no more current states.")
		}

		currentStates = newStates
	}

	/**
	 * Returns the current states of the runner.
	 * @return the states the runner is currently in.
	 */
	fun getCurrentStates() : Set<State> {
		return currentStates
	}

	/**
	 * Returns the runner's automaton.
	 * @return The automaton given in the constructor of the instance.
	 */
	fun getAutomaton() : Automaton {
		return automaton
	}
}