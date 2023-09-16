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
}