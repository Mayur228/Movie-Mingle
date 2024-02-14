package com.theappmakerbuddy.moviemingle.search.di

import com.theappmakerbuddy.moviemingle.search.data.repository.SearchRepositoryImpl
import com.theappmakerbuddy.moviemingle.search.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {
    @Binds
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}
