package com.example.compose_pizzeria.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.ProductoDTO
import com.example.compose_pizzeria.data.TIPO_PRODUCTO

@Composable
fun Home(viewModel: HomeViewModel) {
    val productos: List<ProductoDTO> by viewModel.productos.observeAsState(initial = emptyList())

    LazyColumn {
        item { Text("Pizzas") }
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.PIZZA }) { pizza -> ProductoItem(pizza, viewModel.obtenerImagen(pizza.nombre))
        }
        item { Text("Pasta") }
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.PASTA }) { pasta -> ProductoItem(pasta, viewModel.obtenerImagen(pasta.nombre))
        }
        item { Text("Bebidas") }
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.BEBIDA }) { bebida -> ProductoItem(bebida, viewModel.obtenerImagen(bebida.nombre))
        }
    }
}

@Composable
fun ProductoItem(productoDTO: ProductoDTO, fito:Int) {

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Home(HomeViewModel())
}
