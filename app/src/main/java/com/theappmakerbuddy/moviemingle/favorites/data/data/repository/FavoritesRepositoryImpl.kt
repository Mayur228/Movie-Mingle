package com.theappmakerbuddy.moviemingle.favorites.data.data.repository

import androidx.lifecycle.LiveData
import com.theappmakerbuddy.moviemingle.favorites.data.data.local.Favorite
import com.theappmakerbuddy.moviemingle.favorites.data.data.local.FavoritesDatabase
import com.theappmakerbuddy.moviemingle.favorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val database: FavoritesDatabase):
    FavoritesRepository {
    override suspend fun insertFavorite(favorite: Favorite) {
        database.dao.insertFavorite(favorite)
    }

    override fun getFavorites(): LiveData<List<Favorite>> {
        return database.dao.getAllFavorites()
    }

    override fun isFavorite(mediaId: Int): LiveData<Boolean>{
        return database.dao.isFavorite(mediaId)
    }

    override fun getAFavorites(mediaId: Int): LiveData<Favorite?> {
        return database.dao.getAFavorites(mediaId)
    }

    override suspend fun deleteOneFavorite(favorite: Favorite) {
        database.dao.deleteAFavorite(favorite)
    }

    override suspend fun deleteAllFavorites() {
        database.dao.deleteAllFavorites()
    }
}
