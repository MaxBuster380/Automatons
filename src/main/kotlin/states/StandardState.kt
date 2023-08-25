package states

/**
 * Basic named non-terminal state.
 */
class StandardState(private val name : String) : State {
	override fun toString(): String {
		return "State($name)"
	}
}