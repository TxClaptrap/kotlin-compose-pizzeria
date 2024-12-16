package com.example.compose_pizzeria.data.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://pizzeria-restapi.onrender.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val clienteApi: ClienteApiService by lazy {
        retrofit.create(ClienteApiService::class.java)
    }
    val productoApi: ProductoApiService by lazy {
        retrofit.create(ProductoApiService::class.java)
    }
}