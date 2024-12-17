package com.example.compose_pizzeria.ui.registro

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.compose_pizzeria.data.modelo.ClienteDTO
import com.example.compose_pizzeria.data.repositories.ClienteRepository


class RegistroViewModel(private val clienteRepository: ClienteRepository) : ViewModel() {
    val cliente = MutableLiveData(ClienteDTO())
    val registroActivo = MutableLiveData(false)
    val errorMensaje = MutableLiveData<ErrorMensaje?>(null)
    val isLoading = MutableLiveData(false)

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && target?.let {
            Patterns.EMAIL_ADDRESS.matcher(it).matches()
        } == true
    }

    fun onClienteChange(newCliente: ClienteDTO) {
        errorMensaje.value = ErrorMensaje(
            password = if (newCliente.password.length < 4 && newCliente.password.isNotEmpty()) { "La contraseña debe tener 4 caracteres como mínimo."
            } else null,
            nombre = if (newCliente.nombre.any { it.isDigit() } && newCliente.nombre.isNotEmpty()) { "El nombre no puede contener dígitos."
            } else null,
            email = if (!isValidEmail(newCliente.email) && newCliente.email.isNotEmpty()) { "El correo electrónico no es válido."
            } else null
        )

        registroActivo.value =
                    listOf(
                        newCliente.nombre,
                        newCliente.dni,
                        newCliente.direccion,
                        newCliente.telefono,
                        newCliente.email,
                        newCliente.password
                    ).none { it.isBlank() }

        this.cliente.value = newCliente
    }

    fun onRegistrarClick(respuesta: (Boolean) -> Unit) {
        isLoading.value = true
        val clienteActual = cliente.value
        if (clienteActual != null) {
            viewModelScope.launch {
                val result = clienteRepository.registrarCliente(clienteActual)
                withContext(Dispatchers.Main) {
                    when (result.isSuccess) {
                        true -> {
                            respuesta(true)
                            isLoading.value = false
                            cliente.value = result.getOrThrow()
                        }
                        false -> {
                            respuesta(false)
                            isLoading.value = false
                            Log.d("REGISTRO", "Error:$result")
                        }
                    }
                }
            }
        }
    }
}
