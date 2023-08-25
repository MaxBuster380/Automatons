package states

/**
 * Basic named non-terminal state.
 */
class StandardState(val name : String) : State {
	override fun toString(): String {
		return "State($name)"
	}
}