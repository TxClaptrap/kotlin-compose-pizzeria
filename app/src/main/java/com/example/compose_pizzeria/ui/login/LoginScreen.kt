package com.example.compose_pizzeria.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.ClienteLoginDTO
import com.example.compose_pizzeria.ui.registro.Campo
import modelo.ClienteDTO

@Composable
fun Login(viewModel: LoginViewModel) {
    val loginCliente: ClienteLoginDTO by viewModel.loginCliente.observeAsState(ClienteLoginDTO())
    val loginActivo: Boolean by viewModel.loginActivo.observeAsState(false)

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
            Button(
                onClick = { viewModel.logearCliente() }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 40.dp), enabled = loginActivo
            ) { Text("Iniciar Sesión") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login(viewModel = LoginViewModel())
}