package automatons.deterministicautomatons

import automatons.Automaton
import events.Event
import exceptions.DuplicateTransitionException
import states.State

/**
 * Implementation of DeterministicAutomaton using an unordered list.
 * Same implementation as ListAutomaton, with determinism logic added.
 * @see DeterministicAutomaton
 * @see List
 * @Deprecated
 */
class ListDeterministicAutomaton : DeterministicAutomaton {
    private data class Transition(val startState: State, val event: Event, val endState: State)

    private val transitions = mutableListOf<Transition>()

    // PUBLIC INSTANCE METHODS

    override fun derivateSingle(startState: State, event: Event): State? {
        var res : State? = null

        for (currentTransition in transitions) {
            if (currentTransition.startState == startState && currentTransition.event == event) {
                res = currentTransition.endState
                break
            }
        }

        return res
    }

    override fun add(startState: State, event: Event, endState: State): Automaton {
        val newTransition = Transition(startState, event, endState)

        val transitionAlreadyDefined = derivateSingle(startState, event) != null
        if (transitionAlreadyDefined) {
            throw DuplicateTransitionException("(${startState}, ${event}) is already defined.")
        }

        if (!isRedundant(newTransition)) {
            transitions += newTransition
        }

        return this
    }

    override fun derivate(startState: State, event: Event): Set<State> {
        val res = mutableSetOf<State>()

        val endState = derivateSingle(startState, event)
        if (endState != null) {
            res += endState
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
    private fun isRedundant(newTransition: Transition): Boolean {
        for (currentTransition in transitions) {
            if (currentTransition == newTransition) {
                return true
            }
        }
        return false
    }
}