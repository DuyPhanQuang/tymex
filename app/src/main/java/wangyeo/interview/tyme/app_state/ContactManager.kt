package wangyeo.interview.tyme.app_state

import kotlinx.coroutines.flow.Flow
import wangyeo.interview.repository.model.Contact

interface ContactManager {
    fun getContactById(id: String): Contact?

    fun updateContactList(contacts: Array<Contact>)

    fun contactListUpdated(): Flow<Array<Contact>>

    fun updateContact(contact: Contact)
}