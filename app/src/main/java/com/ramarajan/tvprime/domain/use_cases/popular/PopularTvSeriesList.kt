package com.ramarajan.tvprime.domain.use_cases.popular

import com.ramarajan.tvprime.data.model.tvseries.PopularTvSeriesResponse
import com.ramarajan.tvprime.domain.TvPrimeRepository
import com.ramarajan.tvprime.core.NetworkResult
import javax.inject.Inject

class PopularTvSeriesList @Inject constructor(private val tvPrimeRepository: TvPrimeRepository) {
    suspend operator fun invoke(lang: String, page: Int) :kotlinx.coroutines.flow.Flow<NetworkResult<PopularTvSeriesResponse>> {
        return tvPrimeRepository.popularList(lang, page)
    }
}