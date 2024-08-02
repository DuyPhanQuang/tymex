package wangyeo.interview.tyme.usecases.impl

import wangyeo.interview.repository.model.Contact
import wangyeo.interview.repository.repository.ContactRepository
import wangyeo.interview.tyme.app_state.ContactManager
import wangyeo.interview.tyme.usecases.LoadListUsecase

class LoadContactListUsecaseImpl(
    private var contactManager: ContactManager,
    private val contactRepository: ContactRepository
): LoadListUsecase<Contact>() {
    override suspend fun loadItems(page: Int): Array<Contact> {
        val contacts = contactRepository.fetchContacts(page)
        contactManager.updateContactList(contacts = contacts)
        return contacts
    }
}
