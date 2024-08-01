package wangyeo.interview.repository.repository

import wangyeo.interview.repository.model.Contact

interface ContactRepository {
    suspend fun fetchContacts(): Array<Contact>
}