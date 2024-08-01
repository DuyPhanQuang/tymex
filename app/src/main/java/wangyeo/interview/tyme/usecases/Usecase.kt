package wangyeo.interview.tyme.usecases

import wangyeo.interview.repository.model.Contact
import wangyeo.interview.repository.repository.Repository
import wangyeo.interview.tyme.app_state.AppState
import wangyeo.interview.tyme.usecases.impl.LoadContactListUsecaseImpl

class Usecase {
    companion object {
        // load list
        fun loadContactListUsecase() : LoadListUsecase<Contact> {
            return LoadContactListUsecaseImpl(
                contactManager = AppState.instance.contactManager(),
                contactRepository = Repository.instance.contactRepository()
            )
        }
    }
}