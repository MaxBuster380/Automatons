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

    override fun getEventsFrom(startState: State): Set<Event> {
        val matches = transitions.filter { it.startState == startState }

        return matches.map{ transition -> transition.event }.toSet()
    }

    override fun getEventsTo(endState: State): Set<Event> {
        val matches = transitions.filter { it.endState == endState }

        return matches.map{ transition -> transition.event }.toSet()
    }

    override fun integrate(event: Event, endState: State): Set<State> {
        val matches = transitions.filter { it.event == event && it.endState == endState }

        return matches.map{ transition -> transition.startState }.toSet()
    }

    override fun getEventsBetween(startState: State, endState: State): Set<Event> {
        val matches = transitions.filter { it.startState == startState && it.endState == endState }

        return matches.map{ transition -> transition.event }.toSet()
    }

    override fun getEndStatesFrom(startState: State): Set<State> {
        val matches = transitions.filter { it.startState == startState }

        return matches.map{ transition -> transition.endState }.toSet()
    }

    override fun getStartStatesFrom(endState: State): Set<State> {
        val matches = transitions.filter { it.endState == endState }

        return matches.map{ transition -> transition.startState }.toSet()
    }

    override fun getEventDestinations(event: Event): Set<State> {
        val matches = transitions.filter { it.event == event }

        return matches.map{ transition -> transition.endState }.toSet()
    }

    override fun getEventOrigins(event: Event): Set<State> {
        val matches = transitions.filter { it.event == event }

        return matches.map{ transition -> transition.startState }.toSet()
    }
}