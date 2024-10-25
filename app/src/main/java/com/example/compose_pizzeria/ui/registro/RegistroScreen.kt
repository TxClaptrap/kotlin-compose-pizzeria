package com.example.compose_pizzeria.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.compose_pizzeria.R
import modelo.ClienteDTO


@Composable
fun Campo(teclado:KeyboardType.Text, label: String, onClienteChange:(String)->Unit, texto:(String)) {
var ver by remember { mutableStateOf(Icons.Filled.) }

}


@Composable
fun Registro(viewModel: RegistroViewModel) {
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
                modifier = Modifier.padding(20.dp).,
                value = cliente.nombre,
                onValueChange = { viewModel.onClienteChange(cliente.copy(nombre = it)) },
                label = {Text("Nombre")},
                placeholder = {Text("Juan")}
                //keyboardOptions = KeyboardOptions.Default
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroPreview() {
    Registro(viewModel = RegistroViewModel())
}