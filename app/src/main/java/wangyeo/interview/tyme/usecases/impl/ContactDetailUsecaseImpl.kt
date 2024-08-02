package wangyeo.interview.tyme.usecases.impl

import kotlinx.coroutines.delay
import wangyeo.interview.repository.model.Contact
import wangyeo.interview.repository.repository.ContactRepository
import wangyeo.interview.tyme.app_state.ContactManager
import wangyeo.interview.tyme.global.AppAnalytics
import wangyeo.interview.tyme.models.Event
import wangyeo.interview.tyme.usecases.ContactDetailUsecase

class ContactDetailUsecaseImpl(
    private val contactManager: ContactManager,
    private val contactRepository: ContactRepository,
    private val analytics: AppAnalytics = AppAnalytics.instance,
): ContactDetailUsecase {
    override suspend fun fetchContactDetailByName(name: String, contact: Contact): Contact {
        return try {
            val updatedContact = contactRepository.fetchContactByName(name = name)

            // add delay to see behaviour:
            // display contact detail data from list contact -> wait 2s -> display latest contact detail data from api
            delay(2000L)

            contactManager.updateContact(updatedContact)
            analytics.trackEvent(Event("USECASE_FETCHED_CONTACT_DETAIL_BY_NAME", 1))
            updatedContact
        } catch (e: Exception) {
            contact
        }
    }

}