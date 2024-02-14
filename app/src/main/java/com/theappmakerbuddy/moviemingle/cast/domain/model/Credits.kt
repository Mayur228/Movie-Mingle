package com.theappmakerbuddy.moviemingle.cast.domain.model

import android.os.Parcelable
import com.theappmakerbuddy.moviemingle.cast.domain.model.Cast
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credits(
    val cast: List<Cast>,
    val id: Int
) : Parcelable
