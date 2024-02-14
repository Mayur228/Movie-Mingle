package com.theappmakerbuddy.moviemingle.filmdetail.presentation

import com.theappmakerbuddy.moviemingle.cast.domain.model.Cast
import com.theappmakerbuddy.moviemingle.cast.domain.model.Credits
import com.theappmakerbuddy.moviemingle.favorites.data.data.local.Favorite

sealed interface FilmDetailsUiEvents {
    data object NavigateBack : FilmDetailsUiEvents
    data class NavigateToCastsScreen(val credits: Credits) :
        FilmDetailsUiEvents

    data class AddToFavorites(val favorite: Favorite) :
        FilmDetailsUiEvents

    data class RemoveFromFavorites(val favorite: Favorite) :
        FilmDetailsUiEvents
    data class NavigateToCastDetails(val cast: Cast) : FilmDetailsUiEvents
}
