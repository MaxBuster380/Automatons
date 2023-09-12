package automatons

import events.Event
import states.State

/**
 * Implementation of Automaton using a set.
 * @see Automaton
 * @see Set
 */
class SetAutomaton : Automaton {
    private data class Transition(val startState: State, val event: Event, val endState: State)

    private val transitions = mutableSetOf<Transition>()

    override fun add(startState: State, event: Event, endState: State): Automaton {
        transitions.add(Transition(startState, event, endState))
        return this
    }

    override fun derivate(startState: State, event: Event): Set<State> {
        val matches = transitions.filter { it.startState == startState && it.event == event }

        return matches.map{ transition -> transition.endState }.toSet()
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