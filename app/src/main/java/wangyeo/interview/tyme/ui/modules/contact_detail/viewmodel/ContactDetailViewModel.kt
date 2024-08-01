package wangyeo.interview.tyme.ui.modules.contact_detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import wangyeo.interview.tyme.app_state.ContactManager
import wangyeo.interview.tyme.models.Event
import wangyeo.interview.tyme.ui.modules.common.BaseViewModel
import wangyeo.interview.repository.model.Contact

class ContactDetailViewModel(
    private val contactId: String,
    private val contactManager: ContactManager,
): BaseViewModel() {
    var contact by mutableStateOf<Contact?>(null)

    fun loadContact() {
        contact = contactManager.getContactById(contactId)
    }

    fun contactId(): String {
        return contactId
    }
}