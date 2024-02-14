package com.theappmakerbuddy.moviemingle.favorites.data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theappmakerbuddy.moviemingle.favorites.data.data.local.Favorite
import com.theappmakerbuddy.moviemingle.favorites.data.data.local.FavoritesDao

@Database(entities = [Favorite::class], version = 4, exportSchema = true)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract val dao: FavoritesDao
}
