package events

/**
 * Basic named event.
 */
class StandardEvent(val name : String) : Event {
	override fun toString(): String {
		return "Event($name)"
	}
}