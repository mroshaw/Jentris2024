package com.ollerenshawit.jentris

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String) {
    object Home : Screens("home_route")
    object AboutJDay : Screens("aboutjday_route")
    object AboutJentris : Screens("aboutjentris_route")
}

//initializing the data class with default parameters
data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "J Day",
                icon = Icons.Filled.Place,
                route = Screens.AboutJDay.route
            ),
            BottomNavigationItem(
                label = "Jentris",
                icon = Icons.Filled.Person,
                route = Screens.AboutJentris.route
            ),
        )
    }
}
