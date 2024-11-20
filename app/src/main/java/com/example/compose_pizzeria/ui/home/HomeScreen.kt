package com.example.compose_pizzeria.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.ProductoDTO
import com.example.compose_pizzeria.data.TIPO_PRODUCTO
import modelo.SIZE
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewModel: HomeViewModel) {
    val numeroProductos: Int by viewModel.numeroProductos.observeAsState(0)

    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                ) // Sombra
        ) {
            TopAppBar(title = {
                Text(
                    text = "THE  DRIPPING  PIZZA",
                    fontSize = 20.sp,
                    color = Color(252, 148, 20)
                )
            },
                actions = {
                    BadgedBox(badge = {
                        if (numeroProductos != 0) {
                            Badge(modifier = Modifier.padding(end = 12.dp, top = 5.dp)) {
                                Text(
                                    text = if (numeroProductos < 100) {
                                        numeroProductos.toString()
                                    } else {
                                        "+99"
                                    }
                                )
                            }
                        }
                    }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.width(100.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.drippingicon),
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                modifier = Modifier.padding(end = 15.dp),
                                contentDescription = "Carrito"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                        alpha = 1f
                    )
                ),
                modifier = Modifier.clip(
                        RoundedCornerShape(
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    )
            )
        }
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = innerPadding // Truquillo para que el contenido no se superponga a la barra de herramientas
        ) {
            item {
                OutlinedCard(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 20.dp, bottom = 10.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )

                ) {
                    Text(
                        "Pizzas",
                        color = Color(138, 86, 54),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )
                }
            }
            items(viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.PIZZA }) { pizza ->
                ProductoItem(pizza, viewModel.obtenerImagen(pizza.nombre), onAddToBasket = {productoDTO, size, i -> viewModel.onAddToBasket(productoDTO, size, i) })
            }
            item {
                OutlinedCard(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(10.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )

                ) {
                    Text(
                        "Pastas",
                        color = Color(138, 86, 54),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )
                }
            }
            items(viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.PASTA }) { pasta ->
                ProductoItem(pasta, viewModel.obtenerImagen(pasta.nombre), onAddToBasket = {productoDTO, size, i -> viewModel.onAddToBasket(productoDTO, size, i) })
            }
            item {
                OutlinedCard(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(10.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )

                ) {
                    Text(
                        "Bebidas",
                        color = Color(138, 86, 54),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )
                }
            }
            items(viewModel.listProductos.filter { it.tipo == TIPO_PRODUCTO.BEBIDA }) { bebida ->
                ProductoItem(bebida, viewModel.obtenerImagen(bebida.nombre), onAddToBasket = {productoDTO, size, i -> viewModel.onAddToBasket(productoDTO, size, i) })
            }
        }
    }
}

@Composable
fun ProductoItem(productoDTO: ProductoDTO, foto: Int, onAddToBasket: (ProductoDTO, SIZE?, Int) -> Unit) {
    var desplegado by rememberSaveable { mutableStateOf(false) }
    var seleccionar: SIZE? by rememberSaveable { mutableStateOf(null) }
    var size: String? by rememberSaveable { mutableStateOf(null) }
    var cantidad by rememberSaveable { mutableIntStateOf(1) }
    val context = LocalContext.current


    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row {
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(foto),
                    contentDescription = productoDTO.nombre,
                    modifier = Modifier.sizeIn(maxWidth = 200.dp, maxHeight = 200.dp)
                )
                Text(String.format(Locale.getDefault(), "%.2f€", productoDTO.precio))
            }

            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .size(width = 170.dp, height = 160.dp)
                    .padding(0.dp, 20.dp, 20.dp, 0.dp)
            ) {
                if (productoDTO.tipo == TIPO_PRODUCTO.BEBIDA) {
                    Text(
                        productoDTO.nombre, fontSize = 18.sp, color = Color(247, 146, 22)
                    )
                    Text(
                        "BIEN FRESQUITA!",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                } else {
                    Text(
                        productoDTO.nombre, fontSize = 14.sp, color = Color(247, 146, 22)
                    )
                    productoDTO.listaIngredientes?.joinToString { it.nombre }
                        ?.let { Text(it, fontSize = 10.sp) }
                }
            }
        }


        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {

            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
            ) {
                if (productoDTO.tipo != TIPO_PRODUCTO.PASTA) {
                    TextButton(onClick = { desplegado = !desplegado }) {
                        Text(size ?: "Tamaño")
                    }

                    DropdownMenu(expanded = desplegado, onDismissRequest = { desplegado = false }) {
                        DropdownMenuItem(onClick = {
                            seleccionar = SIZE.PEQUENA
                            desplegado = false
                            size = "Pequeña"
                        }, text = { Text("Pequeña") })
                        DropdownMenuItem(onClick = {
                            seleccionar = SIZE.MEDIANA
                            desplegado = false
                            size = "Mediana"
                        }, text = { Text("Mediana") })
                        DropdownMenuItem(onClick = {
                            seleccionar = SIZE.GRANDE
                            desplegado = false
                            size = "Grande"
                        }, text = { Text("Grande") })
                    }
                }

                TextButton(onClick = { if (cantidad > 1) cantidad -= 1 }) {
                    Text("-", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = cantidad.toString(),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                TextButton(onClick = { if (cantidad < 99) cantidad += 1 }) {
                    Text("+", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(10.dp))
                TextButton(
                    enabled = seleccionar != null || productoDTO.tipo == TIPO_PRODUCTO.PASTA,
                    onClick = {
                        onAddToBasket(productoDTO, seleccionar, cantidad)
                        Toast.makeText(context, "${productoDTO.nombre} se ha añadido al carrito x${cantidad}.", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (seleccionar != null || productoDTO.tipo == TIPO_PRODUCTO.PASTA) {
                                Color(252, 170, 68) // Color cuando está habilitado
                            } else {
                                Color(255, 200, 133) // Color cuando está deshabilitado
                            }
                        )
                ) {
                    Icon(
                        Icons.Filled.AddShoppingCart,
                        "",
                        tint = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        "Añadir",
                        modifier = Modifier.padding(start = 5.dp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    Home(HomeViewModel())
}
