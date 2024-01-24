package com.ramarajan.tvprime.data

import com.ramarajan.tvprime.data.model.cast.TvSeriesCreditsResponse
import com.ramarajan.tvprime.data.model.details.TvSeriesDetailsResponse
import com.ramarajan.tvprime.data.model.tvseries.PopularTvSeriesResponse
import com.ramarajan.tvprime.data.model.search_tv_series.SearchTvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("tv/popular")
    suspend fun getPopular(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): PopularTvSeriesResponse

    @GET("tv/{series_id}")
    suspend fun getTvSeriesDetail(
        @Path("series_id") seriesId: String,
        @Query("language") language: String?,
    ): Response<TvSeriesDetailsResponse>

    @GET("tv/{seriesId}/credits")
    suspend fun getTvSeriesCredits(
        @Path("series_id") seriesId: String,
        @Query("language") language: String?,
    ): Response<TvSeriesCreditsResponse>

    @GET("search/tv")
    suspend fun searchTvSeries(
        @Query("query") query: String?,
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): Response<SearchTvSeriesResponse>
}