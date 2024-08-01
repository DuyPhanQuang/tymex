package wangyeo.interview.tyme.ui.modules.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import wangyeo.interview.tyme.global.AppAnalytics
import wangyeo.interview.tyme.models.Event

open class BaseViewModel(
    private val analytics: AppAnalytics = AppAnalytics.instance
): ViewModel() {
    var isGlobalLoading by mutableStateOf(false)

    fun showGlobalLoading() {
        isGlobalLoading = true
    }

    fun hideGlobalLoading() {
        isGlobalLoading = false
    }

    private val _errorMessage = MutableLiveData<String>().apply {
        value = ""
    }

    val errorMessage: LiveData<String> = _errorMessage

    fun showErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun trackEvent(event: Event) {
        analytics.trackEvent(event)
    }

    open fun bindEvents() {}
}