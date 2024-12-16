package com.example.compose_pizzeria.data.repositories

import com.example.compose_pizzeria.data.modelo.ClienteDTO
import com.example.compose_pizzeria.data.modelo.LoginDTO

class ClienteRepository(private val apiService: ClienteApiService) {
    suspend fun registrarCliente(cliente: ClienteDTO): Result<ClienteDTO> {
        return try {
            val response = apiService.registrarCliente(cliente)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logearCliente(login: LoginDTO): Result<LoginDTO> {
        return try {
            val response = apiService.loginCliente(login)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}