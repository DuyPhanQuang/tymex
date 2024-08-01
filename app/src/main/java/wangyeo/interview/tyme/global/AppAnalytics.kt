package wangyeo.interview.tyme.global

import wangyeo.interview.tyme.models.Event
import java.lang.Exception

class AnalyticSystem {
    fun record() {}
}

class AppAnalytics(
    private val analyticSystem: AnalyticSystem = AnalyticSystem()
) {
    companion object {
        val instance = AppAnalytics()
    }

    fun trackEvent(event: Event) {
        println("Log event ${event.event} value ${event.value}")
        analyticSystem.record()
    }
}