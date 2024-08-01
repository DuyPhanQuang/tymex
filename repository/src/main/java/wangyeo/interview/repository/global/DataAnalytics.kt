package wangyeo.interview.repository.global

import org.json.JSONObject
import java.lang.Exception

class TrackingSystem {
    fun record() {}
}

class DataAnalytics(private val tracer: TrackingSystem = TrackingSystem()) {
    companion object {
        val instance = DataAnalytics()
    }

    fun trackApi(path: String, method: String, params: JSONObject? = null) {
        println("Log api with path = $path, method: $method and params: ${params?.toString()}")
        tracer.record()
    }

    fun trackApiError(path: String, error: Exception) {
        println("Log api error with path = $path, error: ${error.localizedMessage}")
        tracer.record()
    }
}