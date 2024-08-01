package wangyeo.interview.repository.model


import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

data class Contact (
    var id: String = "079 923 76 11",
    var firstName: String = "David",
    var lastName: String = "Beckham",
    var street: String = "4616 Rue du Bât-D'Argent",
    var city: String = "Kaiseraugst",
    var state: String = "Zug",
    var country: String = "Switzerland",
    var birthday: Date? = null,
    var avatar: String = "https://randomuser.me/api/portraits/med/women/33.jpg",
    var nationality: String = "CH"
): Entity {
    constructor(json: JSONObject): this() {
        this.id = json.getString("phone")

        val nameJson = json.getJSONObject("name")
        this.firstName = nameJson.getString("first")
        this.lastName = nameJson.getString("last")

        val locationJson = json.getJSONObject("location")
        val streetJson = locationJson.getJSONObject("street")
        this.street = String.format("%s %s", streetJson.getString("number"), streetJson.getString("name"))
        this.city = locationJson.getString("city")
        this.state = locationJson.getString("state")
        this.country = locationJson.getString("country")

        val dobJson = json.getJSONObject("dob")
        val inputFormater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        this.birthday = inputFormater.parse(dobJson.getString("date"))

        val pictureJson = json.getJSONObject("picture")
        this.avatar = pictureJson.getString("medium")

        this.nationality = json.getString("nat")
    }

    override fun key(): String {
        return id
    }

    fun fullAddress(): String {
        return "${street}, ${city}, ${state}, $country"
    }

    fun fullName(): String {
        return "$firstName $lastName"
    }

    companion object {
        fun dummyContact(id: String = "dummy_id"): Contact {
            val contact = Contact()
            contact.id = id
            return contact
        }
    }
}