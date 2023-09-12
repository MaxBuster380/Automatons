package exceptions

/**
 * Exception thrown when a high-level automaton receives an instance that already has the properties the encapsulation intends to provide.
 */
class RedundantEncapsulation(text : String) : Exception(text) {
    constructor():this("")
}