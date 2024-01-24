package com.ramarajan.tvprime.data.model.search_tv_series

import com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import com.google.gson.annotations.SerializedName

data class SearchTvSeriesResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<TvSeriesItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)

