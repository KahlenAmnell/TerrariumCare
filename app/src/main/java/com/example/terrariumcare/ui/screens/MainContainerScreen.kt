package com.example.terrariumcare.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.terrariumcare.navigation.Screen
import com.example.terrariumcare.ui.viewmodel.AddTerrariumViewModel
import com.example.terrariumcare.ui.viewmodel.TerrariumListViewModel

@Composable
fun MainContainerScreen(
    terrariumListViewModel: TerrariumListViewModel,
    addTerrariumViewModel: AddTerrariumViewModel
) {
    val navController = rememberNavController()

    val items = listOf(
        NavigationItem("Terrariums", Screen.Dashboard.route, Icons.Default.Home),
        NavigationItem("Animals", Screen.Animals.route, Icons.Default.Person),
        NavigationItem("Calendar", Screen.Calendar.route, Icons.Default.DateRange),
        NavigationItem("Settings", Screen.Settings.route, Icons.Default.Settings),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    viewModel = terrariumListViewModel,
                    onNavigateToAddTerrarium = {
                        navController.navigate(Screen.AddTerrarium.route)
                    },
                    onTerrariumClick = { terrariumId ->

                    }
                )
            }

            composable(Screen.AddTerrarium.route) {
                AddTerrariumScreen(
                    viewModel = addTerrariumViewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.Animals.route) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Animals Screen", color = MaterialTheme.colorScheme.onBackground)
                }
            }
            composable(Screen.Calendar.route) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Calendar Screen", color = MaterialTheme.colorScheme.onBackground)
                }
            }
            composable(Screen.Settings.route) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Settings Screen", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

data class NavigationItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)
