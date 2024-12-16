package com.example.compose_pizzeria.data.modelo

data class ProductoDTO (
    val tipo: TIPO_PRODUCTO,
    val id:Int,
    val nombre:String,
    val precio:Double,
    var size: SIZE?,
    val ingredientes:List<IngredienteDTO>?
)