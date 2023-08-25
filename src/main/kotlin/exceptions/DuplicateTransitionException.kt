package exceptions

import automatons.highlevelautomatons.StandardDeterministicAutomaton

/**
 * Exception thrown when you try to add a transition with a (startState, event) already defined to a deterministic automaton.
 * @see StandardDeterministicAutomaton
 */
class DuplicateTransitionException(text : String) : IllegalArgumentException(text)