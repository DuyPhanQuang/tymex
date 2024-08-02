package wangyeo.interview.repository.api.impl

import org.json.JSONArray
import org.json.JSONObject
import wangyeo.interview.repository.api.BaseApi
import wangyeo.interview.repository.api.ContactApi
import wangyeo.interview.repository.model.Contact

class ContactApiImpl: BaseApi(), ContactApi {
    override suspend fun fetchContacts(page: Int, perPage: Int): Array<Contact> {
        val url = "https://api.github.com/users?per_page=$perPage&page=$page"

        val jsonRes = get(url)
        val jsonArray = jsonRes as JSONArray
        val contactList: MutableList<Contact> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val contact = Contact(jsonArray.getJSONObject(i))
            contactList.add(contact)
        }
        return contactList.toTypedArray()
    }

    override suspend fun fetchContactByName(name: String): Contact {
        val url = "https://api.github.com/users/$name"

        val jsonRes = get(url)
        val jsonObject = jsonRes as JSONObject
        return Contact(jsonObject)
    }
}