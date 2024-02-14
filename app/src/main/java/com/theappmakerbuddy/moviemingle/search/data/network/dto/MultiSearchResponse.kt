package com.theappmakerbuddy.moviemingle.search.data.network.dto


import com.google.gson.annotations.SerializedName
import com.theappmakerbuddy.moviemingle.search.domain.model.Search

data class MultiSearchResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<Search>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
