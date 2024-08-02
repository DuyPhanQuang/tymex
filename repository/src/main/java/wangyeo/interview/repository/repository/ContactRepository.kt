package wangyeo.interview.repository.repository

import wangyeo.interview.repository.model.Contact

interface ContactRepository {
    suspend fun fetchContacts(page: Int): Array<Contact>

    suspend fun fetchContactByName(name: String): Contact
}