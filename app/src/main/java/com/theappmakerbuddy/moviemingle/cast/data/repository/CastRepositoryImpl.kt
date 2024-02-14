package com.theappmakerbuddy.moviemingle.cast.data.repository

import com.theappmakerbuddy.moviemingle.cast.domain.model.Credits
import com.theappmakerbuddy.moviemingle.cast.domain.repository.CastRepository
import com.theappmakerbuddy.moviemingle.common.data.network.TMDBApi
import com.theappmakerbuddy.moviemingle.common.util.Resource
import com.theappmakerbuddy.moviemingle.filmdetail.data.mappers.toDomain
import timber.log.Timber
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(
    private val api: TMDBApi,
) : CastRepository {
    // Series Casts
    override suspend fun getTvSeriesCasts(id: Int): Resource<Credits> {
        val response = try {
            api.getTvSeriesCredits(id)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }

        Timber.d("Series cast $response")
        return Resource.Success(response.toDomain())
    }

    // Movie Casts
    override suspend fun getMovieCasts(id: Int): Resource<Credits> {
        val response = try {
            api.getMovieCredits(id)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }

        Timber.d("Movie Casts $response")
        return Resource.Success(response.toDomain())
    }

    override suspend fun getCastDetails(id: Int): Resource<Unit> {
        val response = try {
            api.getCreditDetails(creditId = id)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Cast Details $response")
        return Resource.Success(Unit)
    }
}
