package com.theappmakerbuddy.moviemingle.filmdetail.data.mappers

import com.theappmakerbuddy.moviemingle.cast.data.network.dto.CastResponse
import com.theappmakerbuddy.moviemingle.cast.domain.model.Cast
import com.theappmakerbuddy.moviemingle.cast.domain.model.Credits
import com.theappmakerbuddy.moviemingle.home.data.network.dto.CreditsResponse

internal fun CreditsResponse.toDomain() = Credits(
    id = id,
    cast = cast.map { it.toDomain() }
)

internal fun CastResponse.toDomain() = Cast(
    adult = adult,
    castId = castId,
    character = character,
    creditId = creditId,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    order = order,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,
)
