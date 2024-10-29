package com.example.compose_pizzeria.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.compose_pizzeria.data.ClienteLoginDTO
import modelo.ClienteDTO

class LoginViewModel {
    val loginCliente = MutableLiveData<ClienteLoginDTO>()
    val loginActivo = MutableLiveData(false)

    fun onClienteChange(newClienteLoginDTO: ClienteLoginDTO) {

        this.loginCliente.value = newClienteLoginDTO

        loginActivo.value =
            loginCliente.value?.let {
                listOf(
                    it.email,
                    it.password
                ).none { it.isBlank() }
            }
    }

    fun logearCliente() {
        loginCliente.value?.let { clienteDTO ->
            Log.d("RegistroViewModel", "ClienteDTO: $clienteDTO")
        }
    }
    }