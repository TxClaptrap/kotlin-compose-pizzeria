package com.example.compose_pizzeria.data.repositories

import com.example.compose_pizzeria.data.modelo.LoginDTO
import com.example.compose_pizzeria.data.modelo.ClienteDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ClienteApiService {
    @POST("/clientes/registro")
    suspend fun registrarCliente(@Body cliente: ClienteDTO): ClienteDTO
    @POST("/clientes/login")
    suspend fun loginCliente(@Body loginDTO: LoginDTO): LoginDTO
    @PUT("/clientes/actualizar")
    suspend fun actualizarCliente(@Body cliente: ClienteDTO): ClienteDTO
    @GET("/clientes")
    suspend fun getUsers(): Response<List<ClienteDTO>>
}