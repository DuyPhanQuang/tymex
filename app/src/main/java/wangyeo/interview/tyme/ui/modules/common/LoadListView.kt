package wangyeo.interview.tyme.ui.modules.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import wangyeo.interview.repository.model.Entity
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.getValue

@Composable
fun <T: Entity> LoadListView(
    viewModel: LoadListViewModel<T>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    itemSort: Comparator<T>? = null,
    itemBuilder: @Composable() (item: T) -> Unit
) = if ((viewModel.loading && !viewModel.isRefreshing) || viewModel.items == null) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize() ){
        CircularProgressIndicator(modifier = Modifier.size(64.dp))
    }
} else {
    println("Reloaded now")

    Surface(Modifier.background(color = Color.Transparent)) {
        val items = viewModel.items
        if (itemSort != null) {
            items!!.sortWith(itemSort)
        }

        if (items!!.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()) {
                Text(text = "Items is empty")
            }
        }
        else {
            val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

            // load more if scrolled to bottom
            LaunchedEffect(reachedBottom) {
                if (reachedBottom && !viewModel.loading) viewModel.loadMore()
            }

            // pull to refresh
            SwipeRefresh(
                state = rememberSwipeRefreshState(viewModel.isRefreshing),
                onRefresh = { viewModel.refresh() },
            ) {
                // infinity list
                LazyColumn(
                    modifier = modifier.padding(horizontal = 16.dp),
                    state = listState,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                ) {
                    items(items.size, { index -> items[index].key() }) { i ->
                        itemBuilder(items[i])
                    }
                }
            }
        }
    }
}

private fun LazyListState.reachedBottom(): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - 1
}