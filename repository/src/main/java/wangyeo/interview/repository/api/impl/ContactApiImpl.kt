package wangyeo.interview.repository.api.impl

import org.json.JSONArray
import wangyeo.interview.repository.api.BaseApi
import wangyeo.interview.repository.api.ContactApi
import wangyeo.interview.repository.model.Contact

class ContactApiImpl: BaseApi(), ContactApi {
    override suspend fun fetchContacts(page: Int, perPage: Int): Array<Contact> {
        val url = "https://api.github.com/users?per_page=$perPage&page=$page"

        val jsonObject = get(url)
        val jsonArray = jsonObject as JSONArray
        val contactList: MutableList<Contact> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val contact = Contact(jsonArray.getJSONObject(i))
            contactList.add(contact)
        }
        return contactList.toTypedArray()
    }
}