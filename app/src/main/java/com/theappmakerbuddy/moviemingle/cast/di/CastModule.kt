package com.theappmakerbuddy.moviemingle.cast.di

import com.theappmakerbuddy.moviemingle.cast.data.repository.CastRepositoryImpl
import com.theappmakerbuddy.moviemingle.cast.domain.repository.CastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CastModule {
    @Binds
    abstract fun bindCastRepository(
        castRepositoryImpl: CastRepositoryImpl
    ): CastRepository
}
