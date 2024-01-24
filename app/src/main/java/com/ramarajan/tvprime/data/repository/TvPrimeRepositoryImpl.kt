package com.ramarajan.tvprime.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ramarajan.tvprime.core.Constants
import com.ramarajan.tvprime.data.ApiService
import com.ramarajan.tvprime.data.model.cast.TvSeriesCreditsResponse
import com.ramarajan.tvprime.data.model.details.TvSeriesDetailsResponse
import com.ramarajan.tvprime.data.model.tvseries.*
import com.ramarajan.tvprime.data.model.search_tv_series.SearchTvSeriesResponse
import com.ramarajan.tvprime.data.paging.*
import com.ramarajan.tvprime.domain.TvPrimeRepository
import com.ramarajan.tvprime.core.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TvPrimeRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    TvPrimeRepository {
    override suspend fun popularPagingPagingList(lang: String): Flow<PagingData<TvSeriesItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { PopularPagingSource(apiService, lang) }
    ).flow

    override suspend fun popularList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<PopularTvSeriesResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getPopular(
                    page = page, language = lang
                )
                emit(
                    NetworkResult.Success(response)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                true, null, null, Constants.STS_DEFAULT
                            )
                        }

                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }

                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }


    override suspend fun tvSeriesDetail(
        lang: String,
        seriesId: String
    ): Flow<NetworkResult<Response<TvSeriesDetailsResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getTvSeriesDetail(
                    seriesId = seriesId, language = lang
                )
                emit(
                    if (response.code() == 200){
                        NetworkResult.Success(response)
                    } else{
                        NetworkResult.Failure(
                            true, null, null, Constants.STS_DEFAULT
                        )
                    }

                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                true, null, null, Constants.STS_DEFAULT
                            )
                        }

                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }

                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun tvSeriesCredits(
        lang: String,
        seriesId: String
    ): Flow<NetworkResult<Response<TvSeriesCreditsResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getTvSeriesCredits(
                    seriesId = seriesId, language = lang
                )
                emit(
                    if (response.isSuccessful) NetworkResult.Success(response) else
                        NetworkResult.Failure(
                            true, null, null, Constants.STS_DEFAULT
                        )
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                true, null, null, Constants.STS_DEFAULT
                            )
                        }

                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }

                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun searchPagingList(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<SearchTvSeriesResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.searchTvSeries(query = query, language = lang, page = 1)
                emit(
                    if (response.isSuccessful) NetworkResult.Success(response) else
                        NetworkResult.Failure(
                            true, null, null, Constants.STS_DEFAULT
                        )
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                true, null, null, Constants.STS_DEFAULT
                            )
                        }

                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }

                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)
    }


}
