package com.ramarajan.tvprime.domain.use_cases.popular

import androidx.paging.PagingData
import com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import com.ramarajan.tvprime.domain.TvPrimeRepository
import javax.inject.Inject

class PopularTvSeriesPagingList @Inject constructor(private val tvPrimeRepository: TvPrimeRepository) {
    suspend operator fun invoke(lang: String) :kotlinx.coroutines.flow.Flow<PagingData<TvSeriesItem>> {
        return tvPrimeRepository.popularPagingPagingList(lang)
    }
}