package exceptions

import automatons.deterministicautomatons.HighLevelDeterministicAutomaton

/**
 * Exception thrown when you try to add a transition with a (startState, event) already defined to a deterministic automaton.
 * @see HighLevelDeterministicAutomaton
 */
class DuplicateTransitionException(text : String) : IllegalArgumentException(text)