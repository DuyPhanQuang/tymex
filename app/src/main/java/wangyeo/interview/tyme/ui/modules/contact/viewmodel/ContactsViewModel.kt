package wangyeo.interview.tyme.ui.modules.contact.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wangyeo.interview.tyme.app_state.ContactManager
import wangyeo.interview.tyme.ui.modules.common.LoadListViewModel
import wangyeo.interview.repository.model.Contact
import wangyeo.interview.tyme.usecases.LoadListUsecase

class ContactsViewModel(
    loadListUsecase: LoadListUsecase<Contact>,
    private val contactManager: ContactManager
): LoadListViewModel<Contact>(
    loadListUsecase = loadListUsecase
) {
    override fun bindEvents() {
        viewModelScope.launch(Dispatchers.Main) {
            contactManager
                .contactListUpdated()
                .collect {
                    println("contacts updated")
                    updateItems(it)
                }
        }
    }
}