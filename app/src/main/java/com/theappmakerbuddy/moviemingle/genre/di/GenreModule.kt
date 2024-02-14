package com.theappmakerbuddy.moviemingle.genre.di

import com.theappmakerbuddy.moviemingle.genre.data.repository.GenreRepositoryImpl
import com.theappmakerbuddy.moviemingle.genre.domain.repository.GenreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GenreModule {
    @Binds
    abstract fun bindGenreRepository(
        genreRepositoryImpl: GenreRepositoryImpl
    ): GenreRepository
}
