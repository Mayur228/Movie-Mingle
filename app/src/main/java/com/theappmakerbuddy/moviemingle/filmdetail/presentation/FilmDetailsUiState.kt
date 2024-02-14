package com.theappmakerbuddy.moviemingle.filmdetail.presentation

import com.theappmakerbuddy.moviemingle.cast.domain.model.Credits
import com.theappmakerbuddy.moviemingle.home.data.network.dto.MovieDetails
import com.theappmakerbuddy.moviemingle.home.data.network.dto.TvSeriesDetails

data class FilmDetailsUiState(
    val credits: Credits? = null,
    val isLoading: Boolean = false,
    val isLoadingCasts: Boolean = false,
    val error: String? = null,
    val errorCasts: String? = null,
    val tvSeriesDetails: TvSeriesDetails? = null,
    val movieDetails: MovieDetails? = null
)
