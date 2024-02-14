package com.theappmakerbuddy.moviemingle.cast.domain.usecase

import com.theappmakerbuddy.moviemingle.cast.domain.repository.CastRepository
import javax.inject.Inject

class GetTvCastUseCase @Inject constructor(
    private val repository: CastRepository,
) {
    suspend operator fun invoke(id: Int) = repository.getTvSeriesCasts(id)
}
