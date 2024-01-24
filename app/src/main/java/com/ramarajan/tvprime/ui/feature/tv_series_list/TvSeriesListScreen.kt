package  com.ramarajan.tvprime.ui.feature.tv_series_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import  com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import com.ramarajan.tvprime.ui.feature.common.TvSeriesItemCard
import com.ramarajan.tvprime.ui.feature.common.TopBar
import  com.ramarajan.tvprime.ui.feature.tv_series_list.components.PaginationProgress
import  com.ramarajan.tvprime.ui.feature.tv_series_list.components.handlePagingResult


@Composable
fun TvSeriesListScreen(
    navController: NavController,
    viewModel: TvSeriesListViewModel = hiltViewModel()
) {
    Scaffold(topBar = {

        TopBar(navController=navController, isSearchEnabled = true)

    }) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            val movieItems = selectList(viewModel)
            val modifier =
                if (movieItems.loadState.append == LoadState.Loading)
                    Modifier.padding(bottom = 80.dp)
                else Modifier.fillMaxSize()
            LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(128.dp), content = {
                items(movieItems.itemCount) { i ->
                    Row {
                        movieItems[i]?.let {
                            TvSeriesItemCard(
                                item = it,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                navController = navController
                            )
                        }
                    }
                }
            })
            if (movieItems.loadState.append == LoadState.Loading)
                PaginationProgress()
            else {
                handlePagingResult(movieItems)
            }
        }
    }
}

@Composable
fun selectList(viewModel: TvSeriesListViewModel): LazyPagingItems<TvSeriesItem> {
    return viewModel.popularTvSeriesPagingItems.collectAsLazyPagingItems()
}
