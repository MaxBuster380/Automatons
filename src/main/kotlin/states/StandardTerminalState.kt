package states

/**
 * Basic named terminal state.
 */
class StandardTerminalState(private val name : String):TerminalState {
	override fun toString(): String {
		return "TerminalState($name)"
	}
}