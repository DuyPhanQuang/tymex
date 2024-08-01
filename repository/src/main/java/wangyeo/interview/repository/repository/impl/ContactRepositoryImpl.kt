package wangyeo.interview.repository.repository.impl

import wangyeo.interview.repository.api.ContactApi
import wangyeo.interview.repository.model.Contact
import wangyeo.interview.repository.repository.ContactRepository

class ContactRepositoryImpl(
    private val contactApi: ContactApi
): ContactRepository {

    override suspend fun fetchContacts(): Array<Contact> {
        return contactApi.fetchContacts()
    }
}

