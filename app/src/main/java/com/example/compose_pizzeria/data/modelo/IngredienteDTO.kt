package com.example.compose_pizzeria.data.modelo

data class IngredienteDTO (
    val id:Int,
    val nombre:String,
    val alergenos:List<String>
)