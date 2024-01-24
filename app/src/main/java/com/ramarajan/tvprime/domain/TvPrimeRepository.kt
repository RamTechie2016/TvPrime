package com.ramarajan.tvprime.domain

import androidx.paging.PagingData
import com.ramarajan.tvprime.core.NetworkResult
import com.ramarajan.tvprime.data.model.cast.TvSeriesCreditsResponse
import com.ramarajan.tvprime.data.model.details.TvSeriesDetailsResponse
import com.ramarajan.tvprime.data.model.search_tv_series.SearchTvSeriesResponse
import com.ramarajan.tvprime.data.model.tvseries.PopularTvSeriesResponse
import com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface TvPrimeRepository {
    
    suspend fun popularPagingPagingList(lang: String): Flow<PagingData<TvSeriesItem>>

    suspend fun popularList(lang: String, page: Int): Flow<NetworkResult<PopularTvSeriesResponse>>

    suspend fun tvSeriesDetail(
        lang: String,
        seriesId: String
    ): Flow<NetworkResult<Response<TvSeriesDetailsResponse>>>

    suspend fun tvSeriesCredits(
        lang: String,
        seriesId: String
    ): Flow<NetworkResult<Response<TvSeriesCreditsResponse>>>

    suspend fun searchPagingList(query: String, lang: String): Flow<NetworkResult<Response<SearchTvSeriesResponse>>>
}