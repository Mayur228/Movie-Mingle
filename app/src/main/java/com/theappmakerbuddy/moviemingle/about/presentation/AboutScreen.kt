package com.theappmakerbuddy.moviemingle.about.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theappmakerbuddy.moviemingle.R
import com.theappmakerbuddy.moviemingle.common.presentation.components.StandardToolbar
import com.theappmakerbuddy.moviemingle.common.presentation.theme.MovieMingleTheme
import com.theappmakerbuddy.moviemingle.common.util.appVersionCode
import com.theappmakerbuddy.moviemingle.common.util.appVersionName
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.theappmakerbuddy.moviemingle.common.presentation.theme.titleText

@Destination
@Composable
fun AboutScreen(
    navigator: DestinationsNavigator
) {
    AboutScreenContent(
        events = { event ->
            when (event) {
                is AboutScreenUiEvents.NavigateBack -> {
                    navigator.popBackStack()
                }
            }
        }
    )
}

@Composable
fun AboutScreenContent(
    events: (AboutScreenUiEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            StandardToolbar(
                title = {
                    Text(
                        text = stringResource(R.string.about_title),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                onBackArrowClicked = {
                    events(AboutScreenUiEvents.NavigateBack)
                },
                modifier = Modifier.fillMaxWidth(),
                showBackArrow = true
            )
        }
    ) { innerPadding ->
        val context = LocalContext.current

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
                /*Image(
                    painterResource(
                        id = R.drawable.muviz
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(8.dp)
                )*/
                Text(
                    text = "Movie Mingle",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    fontFamily = titleText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(
                        R.string.v,
                        context.appVersionName(),
                        context.appVersionCode()
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = stringResource(R.string.about),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    MovieMingleTheme {
        AboutScreenContent(
            events = {}
        )
    }
}
