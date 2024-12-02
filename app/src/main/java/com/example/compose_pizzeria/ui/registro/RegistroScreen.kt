package com.example.compose_pizzeria.ui.registro

import RegistroViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.ErrorMensaje
import modelo.ClienteDTO

@Composable
fun Campo(
    teclado: KeyboardType = KeyboardType.Text,
    label: String,
    onClienteChange: (String) -> Unit,
    texto: String,
    mensajeError: String?
) {
    var esconder by remember { mutableStateOf(true) } // Por defecto, la contraseña está oculta

    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        label = { Text(label, color = Color.Gray) },
        visualTransformation = if (teclado == KeyboardType.Password && esconder) {
            PasswordVisualTransformation() // Asteriscos
        } else {
            VisualTransformation.None // No asteriscos
        },
        trailingIcon = {
            if (teclado == KeyboardType.Password) {
                IconButton(onClick = {
                    // Cambia el estado de visibilidad y el ojo
                    esconder = !esconder
                }) {
                    Icon(
                        imageVector = if (esconder) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = ""
                    )
                }
            }
        },
        onValueChange = onClienteChange,
        value = texto
    )
    if (mensajeError != null) {
        Text(
            text = mensajeError,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun Registro(viewModel: RegistroViewModel) {
    val cliente: ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO())
    val registroActivo: Boolean by viewModel.registroActivo.observeAsState(false)
    val errorMensaje: ErrorMensaje? by viewModel.errorMensaje.observeAsState(null)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
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
                KeyboardType.Text,
                "Nombre",
                { viewModel.onClienteChange(cliente.copy(nombre = it)) },
                cliente.nombre,
                errorMensaje?.nombre
            )
            Campo(
                KeyboardType.Text,
                "DNI",
                { viewModel.onClienteChange(cliente.copy(dni = it)) },
                cliente.dni,
                null
            )
            Campo(
                KeyboardType.Text,
                "Dirección",
                { viewModel.onClienteChange(cliente.copy(direccion = it)) },
                cliente.direccion,
                null
            )
            Campo(
                KeyboardType.Phone,
                "Número de teléfono",
                { viewModel.onClienteChange(cliente.copy(telefono = it)) },
                cliente.telefono,
                null
            )
            Campo(
                KeyboardType.Email,
                "E-mail",
                { viewModel.onClienteChange(cliente.copy(email = it)) },
                cliente.email,
                errorMensaje?.email
            )
            Campo(
                KeyboardType.Password,
                "Contraseña",
                { viewModel.onClienteChange(cliente.copy(password = it)) },
                cliente.password,
                errorMensaje?.password
            )
            Button(
                onClick = { viewModel.onRegistrarClick() }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 40.dp), enabled = registroActivo
            ) { Text("Registar") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroPreview() {
    Registro(viewModel = RegistroViewModel())
}