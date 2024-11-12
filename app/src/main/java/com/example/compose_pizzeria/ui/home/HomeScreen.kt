package com.example.compose_pizzeria.ui.home

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.ProductoDTO
import com.example.compose_pizzeria.data.TIPO_PRODUCTO

@Composable
fun Home(viewModel: HomeViewModel) {
    val productos: List<ProductoDTO> by viewModel.productos.observeAsState(initial = emptyList())

    LazyColumn {
        item { Text("Pizzas",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())}
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.PIZZA }) { pizza -> ProductoItem(pizza, viewModel.obtenerImagen(pizza.nombre))
        }
        item { Text("Pasta",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()) }
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.PASTA }) { pasta -> ProductoItem(pasta, viewModel.obtenerImagen(pasta.nombre))
        }
        item { Text("Bebidas",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()) }
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.BEBIDA }) { bebida -> ProductoItem(bebida, viewModel.obtenerImagen(bebida.nombre))
        }
    }
}

@Composable
fun ProductoItem(productoDTO: ProductoDTO, foto:Int) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column {
            Text(productoDTO.nombre)
            Image(
                painter = painterResource(foto),
                contentDescription = productoDTO.nombre
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Home(HomeViewModel())
}
