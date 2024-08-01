package wangyeo.interview.repository.repository

import wangyeo.interview.repository.api.ContactApi
import wangyeo.interview.repository.api.impl.ContactApiImpl
import wangyeo.interview.repository.repository.impl.ContactRepositoryImpl

class Repository {
    companion object {
        val instance = Repository()
    }

    fun contactRepository(): ContactRepository {
        return ContactRepositoryImpl(
            contactApi = contactApi()
        )
    }

    private fun contactApi(): ContactApi {
        return ContactApiImpl()
    }
}