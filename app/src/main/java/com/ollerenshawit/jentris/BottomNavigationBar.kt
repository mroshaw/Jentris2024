package com.ollerenshawit.jentris

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ollerenshawit.jentris.screens.HomeScreen
import com.ollerenshawit.jentris.screens.JDayScreen
import com.ollerenshawit.jentris.screens.JentrisScreen

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.background)
            ) {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                    NavigationBarItem(
                        selected = navigationItem.route == currentDestination?.route,
                        label = {
                            Text(
                                text = navigationItem.label,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.background)
                            )
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.Home.route) {
                HomeScreen(
                    navController
                )
            }
            composable(Screens.AboutJDay.route) {
                JDayScreen(
                    navController
                )
            }
            composable(Screens.AboutJentris.route) {
                JentrisScreen(
                    navController
                )
            }
        }
    }
}