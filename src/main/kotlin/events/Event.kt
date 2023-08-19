package events

interface Event {
	companion object {
		val SUCCESS = StandardEvent("SUCCESS")
		val FAILURE = StandardEvent("FAILURE")
	}
}