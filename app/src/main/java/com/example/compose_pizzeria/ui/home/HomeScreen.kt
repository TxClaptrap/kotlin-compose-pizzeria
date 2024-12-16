package com.example.compose_pizzeria.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavController
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.modelo.ProductoDTO
import com.example.compose_pizzeria.data.modelo.TIPO_PRODUCTO
import com.example.compose_pizzeria.ui.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.compose_pizzeria.data.modelo.SIZE
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val totalProductos by viewModel.numeroProductos.observeAsState(0)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Drawer(navController, drawerState, scope, listOf(Screen.Home))
        }) {
        Scaffold(
            topBar = { TopBar(drawerState, scope, totalProductos) },
            content = {
                Home(viewModel = viewModel, navController = navController, Modifier.padding(it))
            }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState, scope: CoroutineScope, totalProductos: Int) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
            ) // Sombra
    ) {
        TopAppBar(title = {
            Image(
                painter = painterResource(R.drawable.tdp),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp)
            )
        }, actions = {
            // Badge que muestra el número de productos en el carrito.
            BadgedBox(badge = {
                if (totalProductos != 0) {
                    Badge(modifier = Modifier.padding(end = 12.dp, top = 5.dp)) {
                        Text(
                            text = if (totalProductos < 100) {
                                totalProductos.toString()
                            } else {
                                "+99"
                            }
                        )
                    }
                }
            }) {
                // Icono del carrito y logo.
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
            //E
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 1f
            )
        ), modifier = Modifier.clip(
            RoundedCornerShape(
                bottomEnd = 10.dp, bottomStart = 10.dp
            )
        )
        )
    }
}

@Composable
fun SimpleAlertDialog(navController: NavController) {
// Estado para controlar si se muestra el diálogo
    var showDialog by remember { mutableStateOf(false) }
// Botón que activa el diálogo
    Button(onClick = { showDialog = true }) {
        Text("Cerrar sesion")
    }
// Muestra el diálogo si showDialog está a true
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Cierre de sesión")
            },
            text = {
                Text("¿Estás seguro de que quieres cerrar sesión?")
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    navController.navigate(Screen.Login.route)
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}

@Composable
fun Drawer(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    items: List<Screen>
) {
    ModalDrawerSheet(modifier = Modifier.width(250.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEach { screen ->
                DrawerItem(navController, screen)
                scope.launch { drawerState.close() }
                SimpleAlertDialog(navController)
            }
        }
    }
}

@Composable
fun DrawerItem(navController: NavController, screen: Screen) {
    NavigationDrawerItem(
        label = {
            Text(
                text = "Productos",
                modifier = Modifier.fillMaxWidth(), // Asegura que ocupe todo el ancho
                textAlign = TextAlign.Center // Centra el texto horizontalmente
            )
        },
        selected = false,
        onClick = {
            navController.navigate(screen.route) { launchSingleTop = true }
        }
    )
}



// Pantalla principal que muestra productos categorizados y permite agregar al carrito.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewModel: HomeViewModel, navController: NavController, padding: Modifier) {
    val numeroProductos: Int by viewModel.numeroProductos.observeAsState(0)
    val listaProductoDTO: List<ProductoDTO> by viewModel.listaProductos.observeAsState(listOf())

    // Estructura principal con barra superior y lista de productos.
    Scaffold(topBar = {
        // Barra superior personalizada con un título y un carrito con badge.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                ) // Sombra
        ) {
            TopAppBar(title = {
                Image(
                    painter = painterResource(R.drawable.tdp),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp)
                )
            }, actions = {
                // Badge que muestra el número de productos en el carrito.
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
                    // Icono del carrito y logo.
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
                //E
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                    alpha = 1f
                )
            ), modifier = Modifier.clip(
                RoundedCornerShape(
                    bottomEnd = 10.dp, bottomStart = 10.dp
                )
            )
            )
        }
    }) { innerPadding ->
        // Lista de productos organizados por categoría (pizzas, pastas, bebidas).
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = innerPadding // Truquillo para que el contenido no se superponga a la barra de herramientas
        ) {
            // Categoría "Pizzas"
            item {
                OutlinedCard(
                    modifier = Modifier
                        //.width(300.dp)
                        .padding(top = 30.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .height(55.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "CON LAS MANOS!",
                            color = Color(235, 122, 52),
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            // Listado de pizzas.
            items(listaProductoDTO.filter { it.tipo == TIPO_PRODUCTO.pizza }) { pizza ->
                ProductoItem(pizza,
                    viewModel.obtenerImagen(pizza.nombre),
                    onAddToBasket = { productoDTO, size, i ->
                        viewModel.onAddToBasket(
                            productoDTO, size, i
                        )
                    })
            }
            // Categoría "Pastas"
            item {
                OutlinedCard(
                    modifier = Modifier
                        //.width(300.dp)
                        .padding(top = 30.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .height(55.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "CON TENEDOR!",
                            color = Color(235, 122, 52),
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            // Listado de pastas.
            items(listaProductoDTO.filter { it.tipo == TIPO_PRODUCTO.pasta }) { pasta ->
                ProductoItem(pasta,
                    viewModel.obtenerImagen(pasta.nombre),
                    onAddToBasket = { productoDTO, size, i ->
                        viewModel.onAddToBasket(
                            productoDTO, size, i
                        )
                    })
            }
            // Categoría "Bebidas"
            item {
                OutlinedCard(
                    modifier = Modifier
                        //.width(300.dp)
                        .padding(top = 30.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .height(55.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "HIDRÁTATE!",
                            color = Color(235, 122, 52),
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            // Listado de bebidas.
            items(listaProductoDTO.filter { it.tipo == TIPO_PRODUCTO.bebida }) { bebida ->
                ProductoItem(bebida,
                    viewModel.obtenerImagen(bebida.nombre),
                    onAddToBasket = { productoDTO, size, i ->
                        viewModel.onAddToBasket(
                            productoDTO, size, i
                        )
                    })
            }
        }
    }
}

// Componente para mostrar un producto individual con opciones de tamaño y cantidad.
@Composable
fun ProductoItem(
    productoDTO: ProductoDTO, foto: Int, onAddToBasket: (ProductoDTO, SIZE?, Int) -> Unit
) {
    var desplegado by rememberSaveable { mutableStateOf(false) }
    var seleccionar: SIZE? by rememberSaveable { mutableStateOf(null) }
    var size: String? by rememberSaveable { mutableStateOf(null) }
    var cantidad by rememberSaveable { mutableIntStateOf(1) }
    val context = LocalContext.current

    // Tarjeta para mostrar detalles del producto.
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        // Sección principal del producto.
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
                // Información del producto.
                if (productoDTO.tipo == TIPO_PRODUCTO.bebida) {
                    Text(
                        productoDTO.nombre, fontSize = 18.sp, color = Color(247, 146, 22)
                    )
                    Text(
                        "ICE COLD!", fontSize = 12.sp, modifier = Modifier.padding(top = 20.dp)
                    )
                } else {
                    Text(
                        productoDTO.nombre, fontSize = 14.sp, color = Color(247, 146, 22)
                    )
                    productoDTO.ingredientes?.joinToString { it.nombre }
                        ?.let { Text("$it.", fontSize = 10.sp) }

                }
            }
        }

        // Opciones de tamaño, cantidad y botón para añadir al carrito.
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
            ) {
                // Selector de tamaño para productos distintos de pastas.
                if (productoDTO.tipo != TIPO_PRODUCTO.pasta) {
                    TextButton(onClick = { desplegado = !desplegado }) {
                        Text(size ?: "Tamaño")
                    }

                    DropdownMenu(expanded = desplegado, onDismissRequest = { desplegado = false }) {
                        DropdownMenuItem(onClick = {
                            seleccionar = SIZE.PEQUENA
                            desplegado = false
                            size = "pequeña"
                        }, text = { Text("Pequeña") })
                        DropdownMenuItem(onClick = {
                            seleccionar = SIZE.MEDIANA
                            desplegado = false
                            size = "mediana"
                        }, text = { Text("Mediana") })
                        DropdownMenuItem(onClick = {
                            seleccionar = SIZE.GRANDE
                            desplegado = false
                            size = "grande"
                        }, text = { Text("Grande") })
                    }
                }

                // Control de cantidad con botones "+" y "-".
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
                /*TextButton(
                    enabled = seleccionar != null || productoDTO.tipo == TIPO_PRODUCTO.PASTA,
                    onClick = {
                        onAddToBasket(productoDTO, seleccionar, cantidad)
                        Toast.makeText(
                            context,
                            "${productoDTO.nombre} se ha añadido al carrito x${cantidad}.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                 */

                // Botón para añadir el producto al carrito.
                TextButton(
                    onClick = {
                        when (productoDTO.tipo) {
                            TIPO_PRODUCTO.pizza, TIPO_PRODUCTO.bebida -> if (seleccionar == null) {
                                Toast.makeText(
                                    context, "Por favor, selecciona un tamaño.", Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                onAddToBasket(productoDTO, seleccionar, cantidad)
                                Toast.makeText(
                                    context,
                                    "${productoDTO.nombre} $size x${cantidad} añadido al carrito.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            TIPO_PRODUCTO.pasta -> {
                                onAddToBasket(productoDTO, null, cantidad)
                                Toast.makeText(
                                    context,
                                    "${productoDTO.nombre} x${cantidad} añadido al carrito.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (seleccionar != null || productoDTO.tipo == TIPO_PRODUCTO.pasta) {
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
    /*HomeScreen(
        rememberNavController(),
        HomeViewModel(null)
    )*/
}
