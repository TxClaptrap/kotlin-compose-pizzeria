package modelo

import java.util.Date

data class PedidoDTO (
    val id:Int,
    val fecha:Date = Date(),
    var precioTotal:Float,
    var estadoPedido:Estado,
    val lineaPedidos:List<LineaPedidoDTO>
)