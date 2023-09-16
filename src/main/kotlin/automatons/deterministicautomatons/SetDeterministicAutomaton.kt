package automatons

import automatons.deterministicautomatons.DeterministicAutomaton
import events.Event
import exceptions.DuplicateTransitionException
import states.State

/**
 * Implementation of DeterministicAutomaton using a set.
 * Same implementation as SetAutomaton, with determinism logic added.
 * @see DeterministicAutomaton
 * @see Set
 */
class SetDeterministicAutomaton : DeterministicAutomaton {
    private data class Transition(val startState: State, val event: Event, val endState: State)

    private val transitions = mutableSetOf<Transition>()
    override fun derivateSingle(startState: State, event: Event): State? {
        return try {
            transitions.first {
                it.startState == startState && it.event == event
            }.endState
        } catch (_ : Exception) {
            null
        }
    }

    override fun add(startState: State, event: Event, endState: State): Automaton {
        val new = Transition(startState, event, endState)

        val transitionAlreadyDefined = derivateSingle(startState, event) != null
        if (transitionAlreadyDefined) {
            throw DuplicateTransitionException("(${startState}, ${event}) is already defined.")
        }

        transitions.add(new)
        return this
    }

    override fun derivate(startState: State, event: Event): Set<State> {
        val match = derivateSingle(startState, event)

        return if (match != null) {
            setOf(match)
        } else {
            setOf()
        }
    }

    override fun getStates(): Set<State> {
        val startStates = transitions.map { it.startState }.toSet()
        val endStates = transitions.map { it.endState }.toSet()

        return startStates + endStates
    }

    override fun getEvents(): Set<Event> {
        return transitions.map { it.event }.toSet()
    }
}