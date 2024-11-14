package com.example.compose_pizzeria.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_pizzeria.data.ProductoDTO
import com.example.compose_pizzeria.data.TIPO_PRODUCTO
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Home(viewModel: HomeViewModel) {
    val productos: List<ProductoDTO> by viewModel.productos.observeAsState(initial = emptyList())

    LazyColumn {
        item {
            Text(
                "Pizzas",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.PIZZA }) { pizza ->
            ProductoItem(pizza, viewModel.obtenerImagen(pizza.nombre))
        }
        item {
            Text(
                "Pasta",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.PASTA }) { pasta ->
            ProductoItem(pasta, viewModel.obtenerImagen(pasta.nombre))
        }
        item {
            Text(
                "Bebidas",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(
            viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.BEBIDA }) { bebida ->
            ProductoItem(bebida, viewModel.obtenerImagen(bebida.nombre))
        }
    }
}

@Composable
fun ProductoItem(productoDTO: ProductoDTO, foto: Int) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row { Image(
            //modifier = Modifier
            //.size(200.dp)
            //.align(Alignment.Hori), // Alinea la imagen en la parte superior izquierda
            painter = painterResource(foto),
            contentDescription = productoDTO.nombre,
        )
            Column(modifier = Modifier.size(170.dp).padding(0.dp, 20.dp, 20.dp, 0.dp)){
                Text(productoDTO.nombre + ":",
                    fontSize = 16.sp)
                productoDTO.listaIngredientes?.joinToString { it.nombre }?.let { Text(it, fontSize = 10.sp) }
            }
        }


        Column (modifier = Modifier.align(Alignment.CenterHorizontally)) {


            var cantidad by rememberSaveable { mutableIntStateOf(1) }
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                TextButton(
                    onClick = { if (cantidad > 1) cantidad -= 1 }
                ) {
                    Text("-")
                }
                Text(
                    text = cantidad.toString(),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                TextButton(
                    onClick = { cantidad += 1 }
                ) {
                    Text("+")
                }
            }
            TextButton(onClick = {}) {
                Icon(Icons.Filled.AddShoppingCart, "")
                Text("Añadir al carrito")
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    Home(HomeViewModel())
}
