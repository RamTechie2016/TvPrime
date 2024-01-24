package com.ramarajan.tvprime.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramarajan.tvprime.data.ApiService
import com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import com.ramarajan.tvprime.core.Constants.NETWORK_PAGE_SIZE
import com.ramarajan.tvprime.core.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class PopularPagingSource(private val apiService: ApiService, private val lang:String) : PagingSource<Int, TvSeriesItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSeriesItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val data = apiService.getPopular(
                page = position,
                language = lang
            )
            val nextKey = if (data.results?.isEmpty() == true) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            val prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1
            LoadResult.Page(
                data = data.results!!,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvSeriesItem>): Int? {
        return state.anchorPosition
    }
}