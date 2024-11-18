package modelo

import com.example.compose_pizzeria.data.ProductoDTO
import java.util.Locale

data class LineaPedidoDTO (
    val id:Int = 0,
    val size: SIZE? = null,
    var cantidad:Int,
    val productoDTO: ProductoDTO
) {
    override fun toString(): String {
        return String.format(Locale.getDefault(), "%d %s %s", cantidad, productoDTO, size)
    }
}