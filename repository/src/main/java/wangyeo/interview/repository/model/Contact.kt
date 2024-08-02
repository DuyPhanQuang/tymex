package wangyeo.interview.repository.model

import org.json.JSONObject

data class Contact (
    var id: Int = 1,
    var name: String = "mojombo",
    var htmlUrl: String = "https://github.com/mojombo",
    var avatarUrl: String = "https://avatars.githubusercontent.com/u/1?v=4",
    var realName: String? = null,
    var location: String? = null,
    var following: Int? = null,
    var followers: Int? = null,
): Entity {
    constructor(json: JSONObject): this() {
        this.id = json.getInt("id")
        this.name = json.getString("login")
        this.htmlUrl = json.getString("html_url")
        this.avatarUrl = json.getString("avatar_url")
        this.realName = json.optString("name")
        this.location = json.optString("location")
        this.following = json.optInt("following")
        this.followers = json.optInt("followers")
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