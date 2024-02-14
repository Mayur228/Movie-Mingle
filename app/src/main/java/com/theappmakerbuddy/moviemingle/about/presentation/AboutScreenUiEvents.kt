package com.theappmakerbuddy.moviemingle.about.presentation

sealed interface AboutScreenUiEvents {
    data object NavigateBack : AboutScreenUiEvents
}
