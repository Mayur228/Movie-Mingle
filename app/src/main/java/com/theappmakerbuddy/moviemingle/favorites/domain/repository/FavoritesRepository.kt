package com.theappmakerbuddy.moviemingle.favorites.domain.repository

import androidx.lifecycle.LiveData
import com.theappmakerbuddy.moviemingle.favorites.data.data.local.Favorite

interface FavoritesRepository {
    suspend fun insertFavorite(favorite: Favorite)
    fun getFavorites(): LiveData<List<Favorite>>
    fun isFavorite(mediaId: Int): LiveData<Boolean>
    fun getAFavorites(mediaId: Int): LiveData<Favorite?>
    suspend fun deleteOneFavorite(favorite: Favorite)
    suspend fun deleteAllFavorites()
}
