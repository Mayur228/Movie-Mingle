package com.theappmakerbuddy.moviemingle.account.presentation

sealed interface AccountScreenUiEvents {
    data object NavigateToAbout : AccountScreenUiEvents
    data object OpenSocialsDialog : AccountScreenUiEvents
    data object OnRateUsClicked : AccountScreenUiEvents
    data object OnShareClicked : AccountScreenUiEvents
}
