package events

/**
 * Basic named event.
 */
class StandardEvent(private val name : String) : Event {
	override fun toString(): String {
		return "Event($name)"
	}
}