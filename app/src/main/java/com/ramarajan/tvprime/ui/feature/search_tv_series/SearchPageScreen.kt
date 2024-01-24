package  com.ramarajan.tvprime.ui.feature.search_tv_series

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ramarajan.tvprime.ui.feature.common.ErrorView
import com.ramarajan.tvprime.ui.feature.common.IsLoading
import com.ramarajan.tvprime.ui.feature.tv_series_detail.components.SearchBar
import com.ramarajan.tvprime.ui.feature.search_tv_series.components.SearchEmpty
import com.ramarajan.tvprime.ui.feature.search_tv_series.components.SearchTvSeriesItemCard

@Composable
fun SearchPageScreen(
    navController: NavController,
    viewModel: SearchPageViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SearchBar(
                    onSearch = { state ->
                        state.value.text.let { query ->
                            if (query.isNotBlank()) {
                                viewModel.searchTvSeries(query)
                            }
                        }
                        viewModel.searchTvSeries(state.value.text)
                    },
                    onCancel = { viewModel.clearSearch() })
            }
        }

        Box(modifier = Modifier.padding(top = 10.dp)) {
            SearchItemList(viewModel, navController)
            IsLoading(isLoading = viewModel.isLoading.value)
            ErrorView(viewModel.apiError.value)
            SearchEmpty(viewModel.listEmpty.value)
        }
    }
}

@Composable
fun SearchItemList(viewModel: SearchPageViewModel, navController: NavController) {
    LazyColumn {
        items(
            items = viewModel.searchTvSeriesPagingItems,
            key = { item ->
                item.id.toString()
            }
        ) { item ->
            SearchTvSeriesItemCard(item, navController)
        }
    }
}


