package  com.ramarajan.tvprime.ui.feature.tv_series_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ramarajan.tvprime.core.Constants
import com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import com.ramarajan.tvprime.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesListViewModel @Inject constructor(useCases: UseCases) : ViewModel() {
    var popularTvSeriesPagingItems: Flow<PagingData<TvSeriesItem>> = emptyFlow()

    init {
        viewModelScope.launch {
            popularTvSeriesPagingItems = useCases.popularTvSeriesPagingList.invoke(Constants.LANG)
        }
    }
}