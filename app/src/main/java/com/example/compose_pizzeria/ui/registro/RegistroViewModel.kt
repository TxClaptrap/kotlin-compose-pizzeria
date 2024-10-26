import android.util.Log
import androidx.lifecycle.MutableLiveData
import modelo.ClienteDTO

class RegistroViewModel {
    val cliente = MutableLiveData(ClienteDTO())
    val registroActivo = MutableLiveData(false)
    val errorNombre = MutableLiveData<String?>(null)
    val errorEmail = MutableLiveData<String?>(null)
    val errorPassword = MutableLiveData<String?>(null)

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

        //Lo que pide:

        // Validación pass
        errorPassword.value = if (newCliente.password.length < 4) { //Recuerda el .value en MutableLiveData
            "La contraseña debe tener 4 caracteres como mínimo."
        } else null

        // Validación mail
        errorEmail.value =
            if (!newCliente.email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
                "El correo electrónico no es válido."
            } else null

        // Validación nombre
        errorNombre.value = if (newCliente.nombre.any { it.isDigit() }) {
            "El nombre no puede contener dígitos."
        } else null

        // Hay errores antes de habilitar el registro?
        registroActivo.value =
            listOf(errorNombre.value, errorEmail.value, errorPassword.value).all { it == null } &&
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
