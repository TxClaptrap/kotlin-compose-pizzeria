import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compose_pizzeria.data.ErrorMensaje
import modelo.ClienteDTO


class RegistroViewModel(clienteRepository: Any?) : ViewModel() {
    val cliente = MutableLiveData(ClienteDTO())
    val registroActivo = MutableLiveData(false)
    val errorMensaje = MutableLiveData<ErrorMensaje?>(null)


    /*fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }*/

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && target?.let {
            Patterns.EMAIL_ADDRESS.matcher(it).matches()
        } == true
    }

    fun onClienteChange(newCliente: ClienteDTO) {

        //Validaciones
        errorMensaje.value = ErrorMensaje(
            password = if (newCliente.password.length < 4 && newCliente.password.isNotEmpty()) { "La contraseña debe tener 4 caracteres como mínimo."
            } else null,
            nombre = if (newCliente.nombre.any { it.isDigit() } && newCliente.nombre.isNotEmpty()) { "El nombre no puede contener dígitos."
            } else null,
            email = if (!isValidEmail(newCliente.email) && newCliente.email.isNotEmpty()) { "El correo electrónico no es válido."
            } else null
        )

        // Hay errores antes de habilitar el registro?
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

    fun onRegistrarClick() {

        /*val clienteDTO = cliente.value
        if (clienteDTO != null) {
            Log.d("RegistroViewModel", "ClienteDTO: $clienteDTO")
        }*/

        cliente.value?.let { cliente ->
            Log.d("Registro", "Cliente: $cliente")
        }
    }

}
