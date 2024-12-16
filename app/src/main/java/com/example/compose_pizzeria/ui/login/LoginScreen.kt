package com.example.compose_pizzeria.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.modelo.LoginDTO
import com.example.compose_pizzeria.ui.navigation.Screen
import com.example.compose_pizzeria.ui.registro.Campo

@Composable
fun Login(viewModel: LoginViewModel, navController: NavController) {
    val loginCliente: LoginDTO by viewModel.loginCliente.observeAsState(LoginDTO())
    val loginActivo: Boolean by viewModel.loginActivo.observeAsState(false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val texto = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        item {
            Image(
                painter = painterResource(R.drawable.dripping),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .size(350.dp)
            )
        }
        item {

            Campo(
                KeyboardType.Email,
                "E-mail",
                { viewModel.onClienteChange(loginCliente.copy(email = it)) },
                loginCliente.email,
                null
            )
            Campo(
                KeyboardType.Password,
                "Contraseña",
                { viewModel.onClienteChange(loginCliente.copy(password = it)) },
                loginCliente.password,
                null
            )
            Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(50.dp))
                }
            }
            Button(
                onClick = {
                    viewModel.onLogearClick { respuesta ->
                        if (respuesta) {
                            Toast.makeText(texto, "Login correcto", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.Home.route)
                        }
                        else {
                            Toast.makeText(texto, "Login incorrecto", Toast.LENGTH_SHORT).show()
                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp), enabled = loginActivo && !isLoading
            ) { Text("Iniciar Sesión") }
            TextButton(
                onClick = { navController.navigate(Screen.Registro.route) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Regístrate aquí")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    //Login(LoginViewModel(), rememberNavController())
}