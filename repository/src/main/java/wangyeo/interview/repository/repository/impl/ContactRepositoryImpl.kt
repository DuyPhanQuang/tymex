package wangyeo.interview.repository.repository.impl

import wangyeo.interview.repository.api.ContactApi
import wangyeo.interview.repository.model.Contact
import wangyeo.interview.repository.repository.ContactRepository

const val kPerPage = 20

class ContactRepositoryImpl(
    private val contactApi: ContactApi
): ContactRepository {

    override suspend fun fetchContacts(page: Int): Array<Contact> {
        return contactApi.fetchContacts(page, perPage = kPerPage)
    }

    override suspend fun fetchContactByName(name: String): Contact {
        return contactApi.fetchContactByName(name)
    }
}

