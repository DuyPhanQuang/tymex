package wangyeo.interview.tyme.usecases

import wangyeo.interview.repository.model.Entity

open class LoadListUsecase<Item: Entity> {
    fun forceToRefresh() { }

    open suspend fun loadItems(page: Int): Array<Item> {
        return arrayOf<Any>() as Array<Item>
    }
}