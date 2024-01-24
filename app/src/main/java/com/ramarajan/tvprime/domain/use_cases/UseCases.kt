package com.ramarajan.tvprime.domain.use_cases

import com.ramarajan.tvprime.domain.use_cases.details.TvSeriesCredits
import com.ramarajan.tvprime.domain.use_cases.details.TvSeriesDetail
import com.ramarajan.tvprime.domain.use_cases.popular.PopularTvSeriesList
import com.ramarajan.tvprime.domain.use_cases.popular.PopularTvSeriesPagingList
import com.ramarajan.tvprime.domain.use_cases.search_tv_series.SearchTvSeriesPagingList

data class UseCases(
    val popularTvSeriesPagingList: PopularTvSeriesPagingList,
    val popularTvSeriesList: PopularTvSeriesList,
    val tvSeriesDetail: TvSeriesDetail,
    val tvSeriesCredits: TvSeriesCredits,
    val searchTvSeriesPagingList: SearchTvSeriesPagingList,
)