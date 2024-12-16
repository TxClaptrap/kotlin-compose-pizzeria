package com.example.compose_pizzeria.data.modelo

import java.util.Date

data class PedidoDTO (
    val id:Int = 0,
    val fecha:Date = Date(),
    var precioTotal:Double = 0.0,
    var estadoPedido: ESTADO = ESTADO.PENDIENTE,
    val lineaPedidos:List<LineaPedidoDTO> = emptyList()
)