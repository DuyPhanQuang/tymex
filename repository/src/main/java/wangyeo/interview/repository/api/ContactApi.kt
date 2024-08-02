package wangyeo.interview.repository.api

import wangyeo.interview.repository.model.Contact

interface ContactApi {
    suspend fun fetchContacts(page: Int, perPage: Int): Array<Contact>
}