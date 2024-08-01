package wangyeo.interview.repository.api

import wangyeo.interview.repository.model.Contact

interface ContactApi {
    suspend fun fetchContacts(): Array<Contact>
}