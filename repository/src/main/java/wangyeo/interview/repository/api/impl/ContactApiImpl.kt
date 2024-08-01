package wangyeo.interview.repository.api.impl

import wangyeo.interview.repository.api.BaseApi
import wangyeo.interview.repository.api.ContactApi
import wangyeo.interview.repository.model.Contact

class ContactApiImpl: BaseApi(), ContactApi {
    override suspend fun fetchContacts(): Array<Contact> {
        val url = "https://randomuser.me/api/?results=5"

        val jsonObject = get(url)
        val jsonArray = jsonObject.getJSONArray("results")
        val contactList: MutableList<Contact> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val contact = Contact(jsonArray.getJSONObject(i))
            contactList.add(contact)
        }
        return contactList.toTypedArray()
    }
}