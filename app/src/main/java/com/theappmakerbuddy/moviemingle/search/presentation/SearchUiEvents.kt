package com.theappmakerbuddy.moviemingle.search.presentation

import com.theappmakerbuddy.moviemingle.search.domain.model.Search

sealed interface SearchUiEvents {
    data class SearchTermChanged(val value: String) : SearchUiEvents
    data class SearchFilm(val searchTerm: String) :
        SearchUiEvents

    data class OpenFilmDetails(val search: Search?) :
        SearchUiEvents
    data object NavigateBack : SearchUiEvents
}
