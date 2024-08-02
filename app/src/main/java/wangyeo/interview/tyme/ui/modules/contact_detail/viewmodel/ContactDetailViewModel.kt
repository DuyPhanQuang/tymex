package wangyeo.interview.tyme.ui.modules.contact_detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wangyeo.interview.tyme.app_state.ContactManager
import wangyeo.interview.tyme.ui.modules.common.BaseViewModel
import wangyeo.interview.repository.model.Contact
import wangyeo.interview.tyme.usecases.ContactDetailUsecase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import wangyeo.interview.tyme.models.Event

class ContactDetailViewModel(
    private val contactId: String,
    private val contactName: String,
    private val contactManager: ContactManager,
    private val contactDetailUsecase: ContactDetailUsecase,
): BaseViewModel() {
    var contact by mutableStateOf<Contact?>(null)

    fun loadContact() {
        contact = contactManager.getContactById(contactId)
    }

    fun contactId(): String {
        return contactId
    }

    fun fetchLatestContact() {
        viewModelScope.launch(Dispatchers.Main) {
            val original = contact!!
            showGlobalLoading()
            try {
                contact = withContext(Dispatchers.IO) {
                    contactDetailUsecase.fetchContactDetailByName(
                    name = contactName,
                    contact = original,
                )}
                trackEvent(Event("VIEWMODEL_FETCHED_LATEST_CONTACT", 1))
            } catch (e: Exception) {
                contact = original
                e.localizedMessage?.let { showErrorMessage(it) }
            } finally {
                hideGlobalLoading()
            }
        }
    }
}