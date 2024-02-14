package com.theappmakerbuddy.moviemingle.cast.presentation.castdetails

sealed interface CastDetailsEvents {
    data object NavigateBack : CastDetailsEvents
}
