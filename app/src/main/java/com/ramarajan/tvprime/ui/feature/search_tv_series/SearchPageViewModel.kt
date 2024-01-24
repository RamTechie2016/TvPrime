package  com.ramarajan.tvprime.ui.feature.search_tv_series

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import  com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import  com.ramarajan.tvprime.domain.use_cases.UseCases
import  com.ramarajan.tvprime.core.Constants
import  com.ramarajan.tvprime.core.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(val useCases: UseCases) : ViewModel() {

    private var _searchTvSeriesPagingItems = mutableStateListOf<TvSeriesItem>()
    val searchTvSeriesPagingItems: List<TvSeriesItem> = _searchTvSeriesPagingItems

    private val _apiError = mutableStateOf(false)
    val apiError: State<Boolean> = _apiError

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _listEmpty = mutableStateOf(false)
    val listEmpty: State<Boolean> = _listEmpty

    fun searchTvSeries(query: String) {
        viewModelScope.launch {
            useCases.searchTvSeriesPagingList.invoke(query, Constants.LANG).collect {
                when (it) {

                    is NetworkResult.Success -> {
                        _searchTvSeriesPagingItems.clear()
                        val results = it.value.body()?.results
                        if (results.isNullOrEmpty()) {
                            _listEmpty.value = true
                        } else {
                            _listEmpty.value = false
                            _searchTvSeriesPagingItems.addAll(results)
                        }
                        _isLoading.value = false

                    }
                    is NetworkResult.Failure -> {
                        _searchTvSeriesPagingItems.clear()
                        _apiError.value = true
                        _isLoading.value = false
                        _listEmpty.value = false
                    }

                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                        _listEmpty.value = false
                    }
                }

            }
        }
    }

    fun clearSearch() {
        _searchTvSeriesPagingItems.clear()
        _listEmpty.value = false
    }
}
