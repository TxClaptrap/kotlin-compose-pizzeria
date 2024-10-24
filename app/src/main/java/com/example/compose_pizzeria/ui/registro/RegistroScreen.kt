package com.example.compose_pizzeria.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose_pizzeria.R
import modelo.ClienteDTO

@Composable
fun Registrtro(viewModel: RegistroViewModel) {
    val cliente: ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO())

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        item {
            Image(
                painter = painterResource(R.drawable.dripping),
                contentDescription = ""
            )
            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = cliente.nombre,
                onValueChange = { viewModel.onClienteChange(cliente.copy(nombre = it)) },
                label = {Text("Nombre")},
                placeholder = {Text("Juan")}
                //keyboardOptions = KeyboardOptions.Default
            )


        }
    }
}