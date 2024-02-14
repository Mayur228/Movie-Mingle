package com.theappmakerbuddy.moviemingle.genre.domain.repository

import com.theappmakerbuddy.moviemingle.common.util.Resource
import com.theappmakerbuddy.moviemingle.genre.domain.model.Genre

interface GenreRepository {
    suspend fun getMovieGenres(): Resource<List<Genre>>
    suspend fun getTvSeriesGenres(): Resource<List<Genre>>
}
