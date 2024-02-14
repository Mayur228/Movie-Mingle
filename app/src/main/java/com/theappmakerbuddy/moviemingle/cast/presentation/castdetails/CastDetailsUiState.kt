package com.theappmakerbuddy.moviemingle.cast.presentation.castdetails

data class CastDetailsUiState(
    val isLoading: Boolean = false,
    val castDetails: Any? = null,
    val error: String? = null
)
