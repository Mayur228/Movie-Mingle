package com.theappmakerbuddy.moviemingle.genre.data.mappers

import com.theappmakerbuddy.moviemingle.genre.data.network.dto.GenresResponse
import com.theappmakerbuddy.moviemingle.genre.domain.model.Genre


internal fun GenresResponse.GenreDto.toDomain() = Genre(
    id = id,
    name = name
)
