package com.example.compose_pizzeria.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compose_pizzeria.data.ClienteLoginDTO

class LoginViewModel(clienteRepository: Any?) : ViewModel() {
    val loginCliente = MutableLiveData<ClienteLoginDTO>()
    val loginActivo = MutableLiveData(false)

    fun onClienteChange(newClienteLoginDTO: ClienteLoginDTO) {

        this.loginCliente.value = newClienteLoginDTO

        loginActivo.value =
            listOf(
                newClienteLoginDTO.email,
                newClienteLoginDTO.password
            ).none { it.isBlank() }

        /*
        loginActivo.value =
            loginCliente.value?.let {
                listOf(
                    it.email,
                    it.password
                ).none { it.isBlank() }
            }
         */
    }

    fun onLogearClick() {
        loginCliente.value?.let { cliente ->
            Log.d("Login", "Cliente: $cliente")
        }
    }
}