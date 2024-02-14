package com.theappmakerbuddy.moviemingle.home.presentation

import androidx.paging.PagingData
import com.theappmakerbuddy.moviemingle.genre.domain.model.Genre
import com.theappmakerbuddy.moviemingle.home.domain.model.Movie
import com.theappmakerbuddy.moviemingle.home.domain.model.Series
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(
    // Movies
    val trendingMovies: Flow<PagingData<Movie>> = emptyFlow(),
    val upcomingMovies: Flow<PagingData<Movie>> = emptyFlow(),
    val topRatedMovies: Flow<PagingData<Movie>> = emptyFlow(),
    val nowPlayingMovies: Flow<PagingData<Movie>> = emptyFlow(),
    val popularMovies: Flow<PagingData<Movie>> = emptyFlow(),

    // Tv Series
    val trendingTvSeries: Flow<PagingData<Series>> = emptyFlow(),
    val onAirTvSeries: Flow<PagingData<Series>> = emptyFlow(),
    val topRatedTvSeries: Flow<PagingData<Series>> = emptyFlow(),
    val airingTodayTvSeries: Flow<PagingData<Series>> = emptyFlow(),
    val popularTvSeries: Flow<PagingData<Series>> = emptyFlow(),

    val selectedFilmOption: String = "Movies",
    val tvSeriesGenres: List<Genre> = emptyList(),
    val moviesGenres: List<Genre> = emptyList(),
    val selectedGenre: Genre? = null,
)
