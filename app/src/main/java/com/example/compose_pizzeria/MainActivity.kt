package com.example.compose_pizzeria

import RegistroViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.DrippingPizzaTheme
import com.example.compose_pizzeria.ui.login.Login
import com.example.compose_pizzeria.ui.login.LoginViewModel
import com.example.compose_pizzeria.ui.registro.Registro

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrippingPizzaTheme {
                Registro(viewModel = RegistroViewModel())
                //Login(viewModel = LoginViewModel())

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroPreview() {
    Registro(viewModel = RegistroViewModel())
}