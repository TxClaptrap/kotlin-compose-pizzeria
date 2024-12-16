package com.example.compose_pizzeria.data.modelo

data class ClienteDTO (
    val id:String = "",
    val dni:String = "",
    val nombre:String = "",
    val direccion:String = "",
    val telefono:String = "",
    val email:String = "",
    val password:String = "",
    val listaPedidos:List<PedidoDTO> = emptyList()
)