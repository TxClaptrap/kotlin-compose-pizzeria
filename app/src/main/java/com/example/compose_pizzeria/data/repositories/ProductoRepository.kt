package com.example.compose_pizzeria.data.repositories

import com.example.compose_pizzeria.data.modelo.ProductoDTO
import retrofit2.Response

class ProductoRepository(private val apiService: ProductoApiService) {
    suspend fun obtenerProductos(): Result<List<ProductoDTO>> {
        return try {
            val response = apiService.getProductos()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}