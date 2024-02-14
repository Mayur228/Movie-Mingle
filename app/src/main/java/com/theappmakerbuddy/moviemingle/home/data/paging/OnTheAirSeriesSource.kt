package com.theappmakerbuddy.moviemingle.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.theappmakerbuddy.moviemingle.common.data.network.TMDBApi
import com.theappmakerbuddy.moviemingle.home.domain.model.Series
import retrofit2.HttpException
import java.io.IOException

class OnTheAirSeriesSource(private val api: TMDBApi) :
    PagingSource<Int, Series>() {
    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        return try {
            val nextPage = params.key ?: 1
            val onAirSeries = api.getOnTheAirTvSeries(nextPage)
            LoadResult.Page(
                data = onAirSeries.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (onAirSeries.results.isEmpty()) null else onAirSeries.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
