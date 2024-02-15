package com.theappmakerbuddy.moviemingle.cast.presentation.castdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theappmakerbuddy.moviemingle.cast.domain.model.Cast
import com.theappmakerbuddy.moviemingle.common.presentation.components.StandardToolbar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.theappmakerbuddy.moviemingle.R
import com.theappmakerbuddy.moviemingle.common.util.AnimatedPreloader

@Destination
@Composable
fun CastDetailsScreen(
    cast: Cast,
    navigator: DestinationsNavigator,
    viewModel: CastDetailsViewModel = hiltViewModel(),
) {
    val castDetailsUiState by viewModel.castDetailsUiState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.getCastDetails(cast.id)
    }

    CastDetailsScreenContent(
        castName = cast.name,
        state = castDetailsUiState,
        onEvent = { event ->
            when (event) {
                is CastDetailsEvents.NavigateBack -> {
                    navigator.popBackStack()
                }
            }
        }
    )
}

@Composable
fun CastDetailsScreenContent(
    castName: String,
    state: CastDetailsUiState,
    onEvent: (CastDetailsEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            StandardToolbar(
                onBackArrowClicked = {
                    onEvent(CastDetailsEvents.NavigateBack)
                },
                title = {
                    Text(
                        text = castName,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                showBackArrow = true
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            if (state.isLoading.not() && state.error != null) {
//                Text(
//                    text = state.error,
//                    color = Color.Red,
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                )
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedPreloader(modifier = Modifier.size(350.dp),animation = R.raw.error_animation)
                    Text(
                        text = "Whoops! An unknown error occurred while fetching data. Don't worry, our team is on it",
                        color = Color.White,
                        modifier = Modifier.padding(15.dp),
                        textAlign = TextAlign.Center
                    )
                    /*Image(
                        modifier = Modifier
                            .size(250.dp),
                        painter = painterResource(id = R.drawable.ic_empty_cuate),
                        contentDescription = null
                    )*/
                }
            }

            if (state.isLoading.not() && state.castDetails != null) {
                // Show cast details
            }
        }
    }
}

@Preview
@Composable
fun CastDetailsScreenPreview() {
    CastDetailsScreenContent(
        castName = "Tom Cruise",
        state = CastDetailsUiState(),
        onEvent = {}
    )
}
