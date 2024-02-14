package com.theappmakerbuddy.moviemingle.cast.domain.repository

import com.theappmakerbuddy.moviemingle.cast.domain.model.Credits
import com.theappmakerbuddy.moviemingle.common.util.Resource

interface CastRepository {
    suspend fun getTvSeriesCasts(id: Int): Resource<Credits>
    suspend fun getMovieCasts(id: Int): Resource<Credits>
    suspend fun getCastDetails(id: Int): Resource<Unit>
}
