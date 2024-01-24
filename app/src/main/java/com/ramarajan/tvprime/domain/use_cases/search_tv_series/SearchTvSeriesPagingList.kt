package com.ramarajan.tvprime.domain.use_cases.search_tv_series

import com.ramarajan.tvprime.data.model.search_tv_series.SearchTvSeriesResponse
import com.ramarajan.tvprime.domain.TvPrimeRepository
import com.ramarajan.tvprime.core.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SearchTvSeriesPagingList @Inject constructor(private val tvPrimeRepository: TvPrimeRepository) {
    suspend operator fun invoke(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<SearchTvSeriesResponse>>> {
        return tvPrimeRepository.searchPagingList(query = query, lang = lang)
    }
}