package com.ramarajan.tvprime.domain.use_cases.details

import com.ramarajan.tvprime.data.model.cast.TvSeriesCreditsResponse
import com.ramarajan.tvprime.domain.TvPrimeRepository
import com.ramarajan.tvprime.core.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class TvSeriesCredits @Inject constructor(private val tvPrimeRepository: TvPrimeRepository) {
    suspend operator fun invoke(lang: String, seriesId: String) :kotlinx.coroutines.flow.Flow<NetworkResult<Response<TvSeriesCreditsResponse>>> {
        return tvPrimeRepository.tvSeriesCredits(lang, seriesId)
    }
}