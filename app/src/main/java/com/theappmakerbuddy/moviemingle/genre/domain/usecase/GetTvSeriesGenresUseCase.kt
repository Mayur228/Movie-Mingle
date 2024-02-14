package com.theappmakerbuddy.moviemingle.genre.domain.usecase

import com.theappmakerbuddy.moviemingle.genre.domain.repository.GenreRepository
import javax.inject.Inject

class GetTvSeriesGenresUseCase @Inject constructor(
    private val repository: GenreRepository
) {
    suspend operator fun invoke() = repository.getTvSeriesGenres()
}
