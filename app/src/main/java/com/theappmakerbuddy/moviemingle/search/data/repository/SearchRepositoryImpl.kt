package com.theappmakerbuddy.moviemingle.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.theappmakerbuddy.moviemingle.search.data.paging.SearchPagingSource
import com.theappmakerbuddy.moviemingle.common.data.network.TMDBApi
import com.theappmakerbuddy.moviemingle.search.domain.model.Search
import com.theappmakerbuddy.moviemingle.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: TMDBApi): SearchRepository {
    override fun multiSearch(queryParam: String): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                SearchPagingSource(api, queryParam)
            }
        ).flow
    }
}
