package com.theappmakerbuddy.moviemingle.filmdetail.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theappmakerbuddy.moviemingle.R
import com.theappmakerbuddy.moviemingle.filmdetail.presentation.FilmDetailsUiEvents
import com.theappmakerbuddy.moviemingle.filmdetail.presentation.FilmDetailsUiState

@Composable
fun FilmInfo(
    modifier: Modifier = Modifier,
    filmType: String,
    state: FilmDetailsUiState,
    onEvents: (FilmDetailsUiEvents) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = if (filmType == "movie") state.movieDetails?.overview.toString() else state.tvSeriesDetails?.overview.toString(),
            fontSize = 14.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(12.dp))

        Genres(
            filmType = filmType,
            state = state
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append(
                        stringResource(R.string.release_date)
                    )
                }
                append(": ")
                append(
                    if (filmType == "movie") state.movieDetails?.releaseDate.toString() else state.tvSeriesDetails?.firstAirDate.toString()
                )
            },
            fontSize = 12.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        CastDetails(
            state = state,
            onEvent = onEvents,
        )
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun Genres(
    filmType: String,
    state: FilmDetailsUiState
) {
    val genres =
        if (filmType == "movie") state.movieDetails?.genres else state.tvSeriesDetails?.genres
    FlowRow {
        if (genres != null) {
            for (genre in genres) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .background(Color.Gray, MaterialTheme.shapes.small),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        text = genre.name,
                        style = MaterialTheme.typography.body2,
                    )
                }
            }
        }
    }
}
