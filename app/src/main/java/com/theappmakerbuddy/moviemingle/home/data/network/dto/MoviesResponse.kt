package com.theappmakerbuddy.moviemingle.home.data.network.dto

import com.google.gson.annotations.SerializedName
import com.theappmakerbuddy.moviemingle.home.domain.model.Movie

data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
