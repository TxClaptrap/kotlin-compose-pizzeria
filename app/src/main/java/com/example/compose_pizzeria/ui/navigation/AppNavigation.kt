package com.example.compose_pizzeria.ui.navigation

import RegistroViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_pizzeria.ui.home.Home
import com.example.compose_pizzeria.ui.home.HomeScreen
import com.example.compose_pizzeria.ui.home.HomeViewModel
import com.example.compose_pizzeria.ui.login.Login
import com.example.compose_pizzeria.ui.login.LoginViewModel
import com.example.compose_pizzeria.ui.registro.Registro

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route // Pantalla inicial
    ) {
        composable(Screen.Login.route) {
            Login(
                viewModel = LoginViewModel(it),
                navController = navController
            )
        }
        composable(Screen.Registro.route) {
            Registro(
                viewModel = RegistroViewModel(it),
                navController = navController
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = HomeViewModel(it),
                navController = navController
            )
        }
    }
}