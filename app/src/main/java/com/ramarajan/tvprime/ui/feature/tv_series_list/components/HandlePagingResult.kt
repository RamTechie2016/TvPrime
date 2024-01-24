package  com.ramarajan.tvprime.ui.feature.tv_series_list.components

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import  com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import com.ramarajan.tvprime.ui.feature.common.ErrorView
import com.ramarajan.tvprime.ui.feature.common.IsLoading

@Composable
fun handlePagingResult(
    tvSeriesItems: LazyPagingItems<TvSeriesItem>,
) {
    tvSeriesItems.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                IsLoading(isLoading = true)
            }
            error != null -> {
                ErrorView(true)
            }
            tvSeriesItems.itemCount < 1 -> {

            }
            else -> {

            }

        }
    }
}