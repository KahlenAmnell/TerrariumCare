package com.example.terrariumcare.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object AddTerrarium : Screen("add_terrarium")
    object Terrarium : Screen("terrarium")
    object Animals : Screen("animals")
    object AddAnimal : Screen("add_animal")
    object Animal : Screen("animal")
    object Calendar : Screen("calendar")
    object AddCareEvent : Screen("add_care_event")
    object Settings : Screen("settings")
}