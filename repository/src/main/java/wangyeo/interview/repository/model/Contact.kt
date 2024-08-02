package wangyeo.interview.repository.model


import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

data class Contact (
    var id: Int = 1,
    var name: String = "mojombo",
    var htmlUrl: String = "https://github.com/mojombo",
    var avatarUrl: String = "https://avatars.githubusercontent.com/u/1?v=4",
): Entity {
    constructor(json: JSONObject): this() {
        this.id = json.getInt("id")
        this.name = json.getString("login")
        this.htmlUrl = json.getString("html_url")
        this.avatarUrl = json.getString("avatar_url")
    }

    override fun key(): String {
        return id.toString()
    }

    companion object {
        fun dummyContact(id: Int = 99): Contact {
            val contact = Contact()
            contact.id = id
            return contact
        }
    }
}