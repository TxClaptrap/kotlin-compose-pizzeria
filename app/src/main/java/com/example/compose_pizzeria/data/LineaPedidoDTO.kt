package modelo

data class LineaPedidoDTO (
    val id:Int,
    var cantidad:Int,
    val pedido: PedidoDTO,
    var pizza: PizzaDTO?,
    var pasta: PastaDTO?,
    var bebida: BebidaDTO?
)