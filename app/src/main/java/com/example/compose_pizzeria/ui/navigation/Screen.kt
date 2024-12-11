package com.example.compose_pizzeria.ui.navigation

sealed class Screen(val route: String) {
    object Registro : Screen("registro")
    object Login : Screen("login")
    object Home : Screen("home")
}


