package com.example.terrariumcare.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.terrariumcare.R
import com.example.terrariumcare.navigation.Screen
import com.example.terrariumcare.ui.theme.TerrariumCareTheme
import com.example.terrariumcare.ui.viewmodel.AddTerrariumViewModel
import com.example.terrariumcare.ui.viewmodel.TerrariumListViewModel

@Composable
fun MainContainerScreen(
    terrariumListViewModel: TerrariumListViewModel,
    addTerrariumViewModel: AddTerrariumViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Dashboard.route

    MainContainerContent(
        currentRoute = currentRoute,
        onNavigateToRoute = { route ->
            if (currentRoute != route) {
                navController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
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
                    onTerrariumClick = { _ -> }
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

@Composable
fun MainContainerContent(
    currentRoute: String,
    onNavigateToRoute: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val items = listOf(
        NavigationItem(
            title = "Terrariums",
            route = Screen.Dashboard.route,
            icon = { Icon(Icons.Default.Home, contentDescription = "Terrariums") }
        ),
        NavigationItem(
            title = "Animals",
            route = Screen.Animals.route,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.pets_24dp_1f1f1f_fill0_wght400_grad0_opsz24),
                    contentDescription = "Animals"
                )
            }
        ),
        NavigationItem(
            title = "Calendar",
            route = Screen.Calendar.route,
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") }
        ),
        NavigationItem(
            title = "Settings",
            route = Screen.Settings.route,
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") }
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = item.icon,
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = { onNavigateToRoute(item.route) },
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
        content(innerPadding)
    }
}

@Preview(showBackground = true)
@Composable
fun MainContainerScreenPreview() {
    TerrariumCareTheme {
        MainContainerContent(
            currentRoute = Screen.Dashboard.route,
            onNavigateToRoute = {}
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Terrariums Screen Preview", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

data class NavigationItem(
    val title: String,
    val route: String,
    val icon: @Composable () -> Unit
)