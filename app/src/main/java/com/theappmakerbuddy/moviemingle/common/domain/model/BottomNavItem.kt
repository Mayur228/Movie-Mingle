package com.theappmakerbuddy.moviemingle.common.domain.model

import com.theappmakerbuddy.moviemingle.R
import com.theappmakerbuddy.moviemingle.destinations.AccountScreenDestination
import com.theappmakerbuddy.moviemingle.destinations.Destination
import com.theappmakerbuddy.moviemingle.destinations.FavoritesScreenDestination
import com.theappmakerbuddy.moviemingle.destinations.HomeScreenDestination

sealed class BottomNavItem(var title: String, var icon: Int, var destination: Destination) {
    data object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )
    data object Favorites: BottomNavItem(
        title = "Favorites",
        icon = R.drawable.ic_star,
        destination = FavoritesScreenDestination
    )
    data object Account: BottomNavItem(
        title = "Account",
        icon = R.drawable.ic_profile,
        destination = AccountScreenDestination
    )
}
