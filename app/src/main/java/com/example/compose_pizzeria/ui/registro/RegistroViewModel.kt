import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.compose_pizzeria.ui.registro.ErrorMensaje
import modelo.ClienteDTO


class RegistroViewModel {
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

        //La forma en la que yo lo quiero hacer:
        /*
        // Validación pass
        var passwordError: String? = null
        if (newCliente.password.length < 4) {
            passwordError = "La contraseña debe tener 4 caracteres como mínimo."
        }

        // Validación mail
        var emailError: String? = null
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        if (!emailRegex.matches(newCliente.email)) {
            emailError = "El correo electrónico no es válido."
        }

        // Validación nombre
        var nombreError: String? = null
        for (char in newCliente.nombre) {
            if (char.isDigit()) {
                nombreError = "El nombre no puede contener dígitos."
                break
            }
        }

        // Asignar el primer mensaje de error no nulo
        if (passwordError != null) {
            errorPassword.value = passwordError
        } else if (emailError != null) {
            errorEmail.value = emailError
        } else if (nameError != null) {
            errorNombre.value = nombreError
        } else {
            errorNombre = null
            errorEmail = null
            errorPassword = null
        }

        // Hay errores antes de habilitar el registro?
        var hayErrores = false

        if (passwordError != null || emailError != null || nombreError != null) {
            hayErrores = true
        }

        for (field in listOf(
            newCliente.nombre,
            newCliente.dni,
            newCliente.direccion,
            newCliente.telefono,
            newCliente.email,
            newCliente.password
        )) {
            if (field.isBlank()) {
                hayErrores = true
                break
            }
        }

        registroActivo.value = !hayErrores
        */
        //Recuerda el .value en MutableLiveData

        //Lo que pide:
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

        /* cliente.value?.let { Log.d("Registro", "Cliente: $it") } MEJOR ASÍ*/

        cliente.value?.let { cliente ->
            Log.d("Registro", "Cliente: $cliente")
        }
    }

}
