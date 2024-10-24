package com.example.compose_pizzeria.ui.registro

import androidx.lifecycle.MutableLiveData
import modelo.ClienteDTO

class RegistroViewModel {
    val cliente = MutableLiveData(ClienteDTO())

    fun onClienteChange(cliente: ClienteDTO) {
        this.cliente.value = cliente
    }
}