package com.example.compose_pizzeria.data.repositories

import com.example.compose_pizzeria.data.modelo.ProductoDTO
import retrofit2.Response
import retrofit2.http.GET

interface ProductoApiService {
    @GET("/productos")
    suspend fun getProductos(): List<ProductoDTO>
}