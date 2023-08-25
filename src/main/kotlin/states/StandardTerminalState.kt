package states

/**
 * Basic named terminal state.
 */
class StandardTerminalState(val name : String):TerminalState {
	override fun toString(): String {
		return "TerminalState($name)"
	}
}