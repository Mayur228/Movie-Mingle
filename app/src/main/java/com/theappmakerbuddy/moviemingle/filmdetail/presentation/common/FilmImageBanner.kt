package com.theappmakerbuddy.moviemingle.filmdetail.presentation.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.theappmakerbuddy.moviemingle.R
import com.theappmakerbuddy.moviemingle.common.presentation.theme.AppBarCollapsedHeight
import com.theappmakerbuddy.moviemingle.common.presentation.theme.AppBarExpendedHeight
import com.theappmakerbuddy.moviemingle.common.presentation.theme.Transparent
import com.theappmakerbuddy.moviemingle.common.presentation.theme.primaryDark
import com.theappmakerbuddy.moviemingle.common.util.Constants
import com.theappmakerbuddy.moviemingle.favorites.data.data.local.Favorite
import com.theappmakerbuddy.moviemingle.filmdetail.presentation.FilmDetailsUiEvents
import com.theappmakerbuddy.moviemingle.filmdetail.presentation.FilmDetailsUiState
import kotlinx.coroutines.delay

@Composable
fun FilmImageBanner(
    modifier: Modifier = Modifier,
    state: FilmDetailsUiState,
    onEvents: (FilmDetailsUiEvents) -> Unit,
    isLiked: Boolean,
    filmType: String,
) {

    val filmName =
        if (filmType == "movie") state.movieDetails?.title.toString() else state.tvSeriesDetails?.name.toString()
    val posterUrl = "${Constants.IMAGE_BASE_UR}/${
        if (filmType == "movie") state.movieDetails?.posterPath else state.tvSeriesDetails?.posterPath
    }"
    val rating =
        if (filmType == "movie") state.movieDetails?.voteAverage?.toFloat()!! else state.tvSeriesDetails?.voteAverage?.toFloat()!!
    val releaseDate =
        if (filmType == "movie") state.movieDetails?.releaseDate.toString() else state.tvSeriesDetails?.firstAirDate.toString()
    val filmId = if (filmType == "movie") state.movieDetails?.id!! else state.tvSeriesDetails?.id!!

    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight
    var scaleState by remember { mutableStateOf(0.1f) }

    val scaleAnimatable = remember { Animatable(0.1f) }

    Box(modifier = modifier) {

        LaunchedEffect(posterUrl) {
            while (true) {
                // Animate the scale from 1.0 to 0.8 over 2 seconds
                scaleAnimatable.animateTo(2f, animationSpec = tween(2000))
                // Reset the scale to 1.0 for the next iteration
//                scaleAnimatable.animateTo(0.1f, )
            }
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(posterUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_placeholder),
            contentDescription = "Movie Banner",
            contentScale = ContentScale.None,
            modifier = Modifier
                .fillMaxSize()
                .height(imageHeight)
                .graphicsLayer(scaleX = scaleAnimatable.value, scaleY = scaleAnimatable.value)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        // Use the scale factor from zoom to handle pinch-to-zoom gesture
                        scaleState *= zoom
                        // Limit the minimum and maximum scale factors if needed
                        scaleState = scaleState.coerceIn(0.5f, 2.0f)
                    }
                },
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            Pair(0.3f, Transparent),
                            Pair(1.5f, primaryDark)
                        )
                    )
                )
        )
        FilmNameAndRating(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            filmName = filmName,
            rating = rating
        )


        FilmActions(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.TopStart),
            onEvents = onEvents,
            isLiked = isLiked,
            filmId = filmId,
            filmType = filmType,
            posterUrl = posterUrl,
            filmName = filmName,
            releaseDate = releaseDate,
            rating = rating
        )
    }
}

@Composable
private fun FilmActions(
    modifier: Modifier = Modifier,
    onEvents: (FilmDetailsUiEvents) -> Unit,
    isLiked: Boolean,
    filmId: Int,
    filmType: String,
    posterUrl: String,
    filmName: String,
    releaseDate: String,
    rating: Float
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CircularBackButtons(
            onClick = {
                onEvents(FilmDetailsUiEvents.NavigateBack)
            }
        )
        CircularFavoriteButtons(
            isLiked = isLiked,
            onClick = { isFav ->
                if (isFav) {
                    onEvents(
                        FilmDetailsUiEvents.RemoveFromFavorites(
                            Favorite(
                                favorite = false,
                                mediaId = filmId,
                                mediaType = filmType,
                                image = posterUrl,
                                title = filmName,
                                releaseDate = releaseDate,
                                rating = rating
                            )
                        )
                    )
                } else {
                    onEvents(
                        FilmDetailsUiEvents.AddToFavorites(
                            Favorite(
                                favorite = true,
                                mediaId = filmId,
                                mediaType = filmType,
                                image = posterUrl,
                                title = filmName,
                                releaseDate = releaseDate,
                                rating = rating
                            )
                        )
                    )
                }
            }
        )
    }
}
