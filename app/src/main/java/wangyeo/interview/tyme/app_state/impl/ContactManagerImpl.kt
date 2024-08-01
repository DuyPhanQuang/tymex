package wangyeo.interview.tyme.app_state.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import wangyeo.interview.tyme.app_state.ContactManager
import wangyeo.interview.repository.model.Contact

class ContactManagerImpl: ContactManager {
    private var contacts: Array<Contact>? = null

    override fun updateContactList(contacts: Array<Contact>) {
        this.contacts = contacts
    }

    override fun getContactById(id: String): Contact? {
        if (contacts == null || contacts!!.isEmpty()) {
            return null
        }

        return contacts!!.firstOrNull { it.id == id }
    }

    private val updatedContactList = MutableSharedFlow<Array<Contact>>()

    override fun contactListUpdated(): Flow<Array<Contact>> {
        return updatedContactList.asSharedFlow()
    }
}
