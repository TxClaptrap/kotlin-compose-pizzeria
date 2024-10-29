package com.example.compose_pizzeria.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import modelo.ClienteDTO

class LoginViewModel {
    val cliente = MutableLiveData(ClienteDTO())
    val loginActivo = MutableLiveData(false)
    val errorPassword = MutableLiveData<String?>(null)

    fun onClienteChange(newCliente: ClienteDTO) {

        this.cliente.value = newCliente
    }

    fun registrarCliente() {

        /*val clienteDTO = cliente.value
        if (clienteDTO != null) {
            Log.d("RegistroViewModel", "ClienteDTO: $clienteDTO")
        }*/

        cliente.value?.let { clienteDTO ->
            Log.d("RegistroViewModel", "ClienteDTO: $clienteDTO")
        }
    }
    }