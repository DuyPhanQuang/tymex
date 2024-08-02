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

open class LoadListViewModel<T: Entity>(
    private val loadListUsecase: LoadListUsecase<T>
): BaseViewModel() {
    var loading by mutableStateOf(false)
    var items by mutableStateOf<Array<T>?>(null)
    var isRefreshing by mutableStateOf(false)
    var isFirstLaunch = false
    var page = 0

    fun start() {
        if (isFirstLaunch && !isRefreshing) {
            return
        }
        viewModelScope.launch(Dispatchers.Main) {
            loading = true
            try {
                items = withContext(Dispatchers.IO) {
                    loadListUsecase.loadItems(page)
                }
                loading = false
                isFirstLaunch = true
                if (isRefreshing) {
                    isRefreshing = false
                }
            } catch (exception: Exception) {
                println("started -> Load list error = $exception")
            }
        }
    }

    fun updateItems(updatedItems: Array<T>) {
        if (items != null && items!!.size == updatedItems.size) {
            items = null
        }
        items = updatedItems
    }

    fun refresh() {
        isRefreshing = true
        page = 0
        start()
    }

    fun loadMore() {
        if(items.isNullOrEmpty()) {
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            try {
                val latestPage = page + 1
                val newItems = withContext(Dispatchers.IO) {
                    loadListUsecase.loadItems(latestPage)
                }
                items = items!! + newItems
                page = latestPage
            } catch (exception: Exception) {
                println("more loaded -> Load list error = $exception")
            }
        }

    }
}