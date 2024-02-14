package com.theappmakerbuddy.moviemingle.cast.presentation.casts

import com.theappmakerbuddy.moviemingle.cast.domain.model.Cast

sealed interface CastsUiEvents {
    data class NavigateToCastDetails(val cast: Cast) :
        CastsUiEvents

    data object NavigateBack : CastsUiEvents
}
