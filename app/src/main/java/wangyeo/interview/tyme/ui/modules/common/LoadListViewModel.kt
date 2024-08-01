package wangyeo.interview.tyme.ui.modules.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wangyeo.interview.tyme.usecases.LoadListUsecase
import wangyeo.interview.repository.model.Entity

open class LoadListViewModel<Item: Entity>(
    private val loadListUsecase: LoadListUsecase<Item>
): BaseViewModel() {
    var loading by mutableStateOf(false)
    var items by mutableStateOf<Array<Item>?>(null)
    var isRefreshing by mutableStateOf(false)
    var isFirstLaunch = false

    fun start() {
        if (isFirstLaunch && !isRefreshing) {
            return
        }
        viewModelScope.launch(Dispatchers.Main) {
            loading = true
            try {
                items = withContext(Dispatchers.IO) {
                    loadListUsecase.loadItems()
                }
                loading = false
                isFirstLaunch = true
                if (isRefreshing) {
                    isRefreshing = false
                }
            } catch (exception: Exception) {
                println("Load list error = $exception")
            }
        }
    }

    fun updateItems(updatedItems: Array<Item>) {
        if (items != null && items!!.size == updatedItems.size) {
            items = null
        }
        items = updatedItems
    }

    fun refresh() {
        isRefreshing = true
        start()
    }
}