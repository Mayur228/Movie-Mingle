package com.theappmakerbuddy.moviemingle.home.data.network.dto

import com.google.gson.annotations.SerializedName
import com.theappmakerbuddy.moviemingle.home.domain.model.Series

data class TvSeriesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Series>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)
