package com.example.compose_pizzeria.data

import modelo.IngredienteDTO
import modelo.SIZE

data class ProductoDTO (
    val tipo:TIPO_PRODUCTO,
    val id:Int,
    val nombre:String,
    val precio:Double,
    var size: SIZE?,
    val listaIngredientes:List<IngredienteDTO>?
)