package com.theappmakerbuddy.moviemingle.filmdetail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.theappmakerbuddy.moviemingle.destinations.CastDetailsScreenDestination
import com.theappmakerbuddy.moviemingle.destinations.CastsScreenDestination
import com.theappmakerbuddy.moviemingle.filmdetail.presentation.common.FilmImageBanner
import com.theappmakerbuddy.moviemingle.filmdetail.presentation.common.FilmInfo

@Destination
@Composable
fun FilmDetailsScreen(
    filmId: Int,
    filmType: String,
    navigator: DestinationsNavigator,
    viewModel: FilmDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getFilmDetails(filmId, filmType)
    }
    val isFilmFavorite = viewModel.isAFavorite(filmId).observeAsState().value != null
    val filmDetailsUiState by viewModel.filmDetailsUiState.collectAsState()

    FilmDetailsScreenContent(
        filmType = filmType,
        isLiked = isFilmFavorite,
        state = filmDetailsUiState,
        onEvents = { event ->
            when (event) {
                is FilmDetailsUiEvents.NavigateBack -> {
                    navigator.popBackStack()
                }

                is FilmDetailsUiEvents.NavigateToCastsScreen -> {
                    navigator.navigate(
                        CastsScreenDestination(event.credits)
                    )
                }

                is FilmDetailsUiEvents.AddToFavorites -> {
                    viewModel.insertFavorite(
                        event.favorite
                    )
                }

                is FilmDetailsUiEvents.RemoveFromFavorites -> {
                    viewModel.deleteFavorite(
                        event.favorite
                    )
                }

                is FilmDetailsUiEvents.NavigateToCastDetails -> {
                    navigator.navigate(CastDetailsScreenDestination(event.cast))
                }
            }
        }
    )
}

@Composable
fun FilmDetailsScreenContent(
    filmType: String,
    state: FilmDetailsUiState,
    onEvents: (FilmDetailsUiEvents) -> Unit,
    isLiked: Boolean,
) {

    Box {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.Center)
            )
        }

        if (state.isLoading.not() &&
            ((filmType == "tv" && state.tvSeriesDetails != null) || (filmType == "movie" && state.movieDetails != null)) &&
            state.error == null
        ) {
            LazyColumn {
                item {
                    FilmImageBanner(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        filmType = filmType,
                        state = state,
                        isLiked = isLiked,
                        onEvents = onEvents,
                    )
                }

                item {
                    FilmInfo(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        filmType = filmType,
                        state = state,
                        onEvents = onEvents,
                    )
                }
            }
        }

        if (
            state.isLoading.not() &&
            state.error != null
        ) {
            Text(
                text = state.error,
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun FilmDetailsScreenPreview() {
    FilmDetailsScreenContent(
        filmType = "tv",
        state = FilmDetailsUiState(),
        onEvents = {},
        isLiked = false,
    )
}
