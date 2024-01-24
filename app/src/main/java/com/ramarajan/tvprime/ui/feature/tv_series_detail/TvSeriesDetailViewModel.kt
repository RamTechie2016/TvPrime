package  com.ramarajan.tvprime.ui.feature.tv_series_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.SavedStateHandle
import  com.ramarajan.tvprime.data.model.cast.TvSeriesCreditsResponse
import  com.ramarajan.tvprime.data.model.details.TvSeriesDetailsResponse
import  com.ramarajan.tvprime.domain.use_cases.UseCases
import  com.ramarajan.tvprime.core.Constants
import  com.ramarajan.tvprime.core.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay

@HiltViewModel
class TvSeriesDetailViewModel @Inject constructor(
    useCases: UseCases, savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _tvSeriesDetailResponse: MutableState<TvSeriesDetailsResponse> =
        mutableStateOf(TvSeriesDetailsResponse())
    val tvSeriesDetailResponse: State<TvSeriesDetailsResponse> = _tvSeriesDetailResponse

    private val _tvSeriesCreditResponse: MutableState<TvSeriesCreditsResponse> =
        mutableStateOf(TvSeriesCreditsResponse())
    val tvSeriesCreditResponse: State<TvSeriesCreditsResponse> = _tvSeriesCreditResponse

    private val _apiError = mutableStateOf(false)

    private var _isLoading = mutableStateMapOf<Int, Boolean>()

    init {
        initMapValues()
      savedStateHandle.get<String>("seriesId")?.let { seriesId ->
            if (seriesId.isNotEmpty()) {
                viewModelScope.launch {
                    useCases.tvSeriesDetail.invoke(Constants.LANG, seriesId).collect {
                        when (it) {

                            is NetworkResult.Success -> {
                                it.value.body()?.let { response ->
                                    _tvSeriesDetailResponse.value = response
                                    delay(1000)
                                    _isLoading[0] = false
                                }

                            }
                            is NetworkResult.Failure -> {
                                _apiError.value = true
                                _isLoading[0] = false
                            }

                            is NetworkResult.Loading -> {
                                _isLoading[0] = true
                            }
                        }

                    }
                    useCases.tvSeriesCredits.invoke(Constants.LANG, seriesId).collect {
                        when (it) {

                            is NetworkResult.Success -> {
                                it.value.body()?.let { response ->
                                    _tvSeriesCreditResponse.value = response
                                    delay(1000)
                                    _isLoading[1] = false
                                }

                            }
                            is NetworkResult.Failure -> {
                                _apiError.value = true
                                _isLoading[1] = false
                            }

                            is NetworkResult.Loading -> {

                            }
                        }

                    }

                }

            }
        }
    }

    private fun initMapValues() {
        _isLoading[0] = true
        _isLoading[1] = true
        _isLoading[2] = true
    }

}