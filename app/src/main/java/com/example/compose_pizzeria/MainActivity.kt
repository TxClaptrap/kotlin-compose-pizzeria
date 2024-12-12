package com.example.compose_pizzeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose.DrippingPizzaTheme
import com.example.compose_pizzeria.ui.home.Home
import com.example.compose_pizzeria.ui.home.HomeViewModel
import com.example.compose_pizzeria.ui.navigation.AppNavigation

class Pizzeria : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrippingPizzaTheme {
                AppNavigation(rememberNavController())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    //Home(viewModel = HomeViewModel(null), rememberNavController(), Modifier.Companion.padding(it))
}