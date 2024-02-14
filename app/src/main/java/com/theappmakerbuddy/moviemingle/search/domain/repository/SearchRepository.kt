package com.theappmakerbuddy.moviemingle.search.domain.repository

import androidx.paging.PagingData
import com.theappmakerbuddy.moviemingle.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun multiSearch(queryParam: String): Flow<PagingData<Search>>
}
