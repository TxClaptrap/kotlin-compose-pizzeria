package com.example.compose_pizzeria.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_pizzeria.data.modelo.ClienteDTO
import com.example.compose_pizzeria.data.modelo.LoginDTO
import com.example.compose_pizzeria.data.repositories.ClienteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val clienteRepository: ClienteRepository) : ViewModel() {
    var cliente: ClienteDTO? = null
    val loginCliente = MutableLiveData<LoginDTO>()
    val loginActivo = MutableLiveData(false)
    val isLoading = MutableLiveData(false)

    fun onClienteChange(newLoginDTO: LoginDTO) {

        this.loginCliente.value = newLoginDTO

        loginActivo.value =
            listOf(
                newLoginDTO.email,
                newLoginDTO.password
            ).none { it.isBlank() }


    }



    fun onLogearClick(respuesta: (Boolean) -> Unit) {
        isLoading.value = true
        val clienteActual = loginCliente.value
        if (clienteActual != null) {
            viewModelScope.launch {
                val result = clienteRepository.logearCliente(clienteActual)
                withContext(Dispatchers.Main) {
                    when (result.isSuccess) {
                        true -> {
                            loginCliente.value = result.getOrThrow()
                            isLoading.value = false
                            respuesta(true)
                        }
                        false -> {
                            isLoading.value = false
                            respuesta(false)
                            Log.d("LOGIN", "Error:$result")
                        }

                    }
                }
            }
        }
    }
}


/*
loginActivo.value =
    loginCliente.value?.let {
        listOf(
            it.email,
            it.password
        ).none { it.isBlank() }
    }
 */

/*fun onLogearClick() {
    loginCliente.value?.let { cliente ->
        Log.d("Login", "Cliente: $cliente")
    }
}*/