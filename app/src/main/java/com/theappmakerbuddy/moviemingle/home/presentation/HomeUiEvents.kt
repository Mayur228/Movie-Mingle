package com.theappmakerbuddy.moviemingle.home.presentation

import com.theappmakerbuddy.moviemingle.genre.domain.model.Genre

sealed interface HomeUiEvents {
    data object OnSearchClick : HomeUiEvents
    data object NavigateBack : HomeUiEvents
    data object OnPullToRefresh : HomeUiEvents

    data class NavigateToFilmDetails(
        val id: Int,
        val filmType: String
    ) : HomeUiEvents

    data class OnFilmGenreSelected(
        val genre: Genre,
        val filmType: String,
        val selectedFilmOption: String
    ) :
        HomeUiEvents

    data class OnFilmOptionSelected(val item: String) :
        HomeUiEvents
}
