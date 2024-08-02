package wangyeo.interview.tyme.usecases

import wangyeo.interview.repository.model.Contact

interface ContactDetailUsecase {
    suspend fun fetchContactDetailByName(name: String, contact: Contact): Contact
}