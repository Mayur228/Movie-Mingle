package com.theappmakerbuddy.moviemingle.home.data.network.dto


import com.google.gson.annotations.SerializedName
import com.theappmakerbuddy.moviemingle.cast.data.network.dto.CastResponse

data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<CastResponse>,
    @SerializedName("id")
    val id: Int
)
