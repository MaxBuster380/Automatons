package automatons

/**
 * Exception thrown when you try to add a transition with a (startState, event) already defined to a deterministic automaton.
 * @see DeterministicAutomaton
 */
class DuplicateTransitionException : IllegalArgumentException()