package wangyeo.interview.tyme.app_state

import wangyeo.interview.tyme.app_state.impl.ContactManagerImpl

class AppState {
    companion object {
        val instance = AppState()
    }

    private lateinit var contactManagerInstance: ContactManager

    fun contactManager(): ContactManager {
        if (::contactManagerInstance.isInitialized) {
            return contactManagerInstance
        }
        contactManagerInstance = ContactManagerImpl()
        return contactManagerInstance
    }
}