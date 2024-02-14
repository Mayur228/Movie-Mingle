package com.theappmakerbuddy.moviemingle.favorites.di

import com.theappmakerbuddy.moviemingle.favorites.data.data.repository.FavoritesRepositoryImpl
import com.theappmakerbuddy.moviemingle.favorites.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesModule {
    @Binds
    abstract fun bindFavoritesRepository(
        favoritesRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository
}
