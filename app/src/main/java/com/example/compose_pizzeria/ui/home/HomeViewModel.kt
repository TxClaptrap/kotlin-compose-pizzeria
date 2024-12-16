package com.example.compose_pizzeria.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.modelo.ProductoDTO
import com.example.compose_pizzeria.data.modelo.TIPO_PRODUCTO
import com.example.compose_pizzeria.data.modelo.IngredienteDTO
import com.example.compose_pizzeria.data.modelo.LineaPedidoDTO
import com.example.compose_pizzeria.data.modelo.SIZE
import com.example.compose_pizzeria.data.repositories.ProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val productoRepository: ProductoRepository) : ViewModel() {
    /*
    //private val lista: List<ProductoDTO> = listOf()
    //val productos = MutableLiveData(lista)
    //O si te gusta más así...:
    //val productos: MutableLiveData<List<ProductoDTO>> = MutableLiveData(listOf())
    //val pedido: MutableLiveData<PedidoDTO> = MutableLiveData(PedidoDTO())
     */

    var listaProductos = MutableLiveData<List<ProductoDTO>>()
    val numeroProductos: MutableLiveData<Int> = MutableLiveData(0)

    init {
        val listaProductosActual = listaProductos.value
        if (listaProductosActual.isNullOrEmpty()) {
            viewModelScope.launch {
                val result = productoRepository.obtenerProductos()
                withContext(Dispatchers.Main) {
                    when (result.isSuccess) {
                        true -> listaProductos.value = result.getOrThrow()
                        false -> Log.d("HOME", "Error:$result")
                    }
                }
            }
        }
        Log.d("PRODUCTOS", listaProductos.value.toString())
    }

    fun cargarProductos() {

        // Ingredientes comunes
        val tomate = IngredienteDTO(2, "Tomate", emptyList())
        val bacon = IngredienteDTO(3, "Bacon", emptyList())
        val champiñones = IngredienteDTO(4, "Champiñones", emptyList())
        val nata = IngredienteDTO(5, "Nata", emptyList())
        val peperoni = IngredienteDTO(6, "Peperoni", emptyList())
        val pimiento = IngredienteDTO(7, "Pimiento", emptyList())
        val cebolla = IngredienteDTO(8, "Cebolla", emptyList())
        val aceitunas = IngredienteDTO(9, "Aceitunas", emptyList())
        val jamonYork = IngredienteDTO(10, "Jamón York", emptyList())
        val quesoDeCabra = IngredienteDTO(11, "Queso de cabra", emptyList())
        val carnePicada = IngredienteDTO(12, "Carne picada", emptyList())
        val salsaBarbacoa = IngredienteDTO(13, "Salsa barbacoa", emptyList())
        val pinya = IngredienteDTO(14, "Piña", emptyList())
        val mozarella = IngredienteDTO(15, "Mozzarella", emptyList())
        val gorgonzola = IngredienteDTO(16, "Gorgonzola", emptyList())
        val parmesano = IngredienteDTO(17, "Parmesano", emptyList())
        val cheddar = IngredienteDTO(18, "Cheddar", emptyList())
        val picante = IngredienteDTO(20, "Salsa picante", emptyList())
        val jalap = IngredienteDTO(21, "Jalapeños", emptyList())

        // Listas de ingredientes
        val ingredientesCarbonaraPizza = listOf(mozarella, bacon, champiñones, nata)
        val ingredientes4Quesos = listOf(mozarella, gorgonzola, parmesano, cheddar, tomate)
        val ingredientesMasPepe = listOf(mozarella, peperoni, tomate)
        val ingredientesVegetariana = listOf(mozarella, pimiento, cebolla, aceitunas, tomate)
        val ingredientesMixta = listOf(mozarella, jamonYork, tomate)
        val ingredientesCabra = listOf(quesoDeCabra, mozarella, tomate)
        val ingredientesBBQ = listOf(carnePicada, bacon, salsaBarbacoa, mozarella)
        val ingredientesAberrante = listOf(mozarella, jamonYork, pinya, tomate)

        // Ingredientes para pastas
        val ingredientesCarbonaraPasta = listOf(nata, bacon, mozarella)
        val ingredientesBolognesePasta = listOf(tomate, carnePicada, mozarella)
        val ingredientes4QuesosPasta = listOf(mozarella, gorgonzola, parmesano, cheddar)
        val ingredientesPicantePasta = listOf(picante, carnePicada, jalap)

        // Lista de productos
        listaProductos.value = listOf(

            // Pizzas
            ProductoDTO(TIPO_PRODUCTO.pizza, 1, "Alucinógena", 10.0, null, ingredientesCarbonaraPizza),
            ProductoDTO(TIPO_PRODUCTO.pizza,2, "Radioactiva", 9.5, null, ingredientes4Quesos),
            ProductoDTO(TIPO_PRODUCTO.pizza,3, "Sarpullida", 8.5, null, ingredientesMasPepe),
            ProductoDTO(TIPO_PRODUCTO.pizza,4, "Alfalfosa", 8.0, null, ingredientesVegetariana),
            ProductoDTO(TIPO_PRODUCTO.pizza,5, "Mixta", 9.0, null, ingredientesMixta),
            ProductoDTO(TIPO_PRODUCTO.pizza,6, "Beeeee!", 10.5, null, ingredientesCabra),
            ProductoDTO(TIPO_PRODUCTO.pizza,7, "Sangrienta", 11.0, null, ingredientesBBQ),
            ProductoDTO(TIPO_PRODUCTO.pizza,8, "Aberrante", 8.5, null, ingredientesAberrante),

            // Pastas
            ProductoDTO(TIPO_PRODUCTO.pasta,13, "Spaghetti Carbonara", 8.0, null, ingredientesCarbonaraPasta),
            ProductoDTO(TIPO_PRODUCTO.pasta,14, "Spaghetti Bolognese", 7.5, null, ingredientesBolognesePasta),
            ProductoDTO(TIPO_PRODUCTO.pasta,15, "Penne 4 Formaggi", 9.0, null, ingredientes4QuesosPasta),
            ProductoDTO(TIPO_PRODUCTO.pasta,16, "Farfalle Birichine", 8.5, null, ingredientesPicantePasta),

            // Bebidas
            ProductoDTO(TIPO_PRODUCTO.bebida,9, "Agua Potable Pura", 1.5, null, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.bebida,10, "Nuka-Cola", 2.0, null, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.bebida,11, "Nuka-Cherry", 2.0, null, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.bebida,12, "Nuka-Cola Quantum", 3.0, null, emptyList())
        )
    }

    //Asignar imágenes a los productos
    fun obtenerImagen(nombre: String) = when(nombre) {
        "Alucinógena" -> R.drawable.carbonara
        "Radioactiva" -> R.drawable.quesos4
        "Sarpullida" -> R.drawable.pepe
        "Alfalfosa" -> R.drawable.vegetariana
        "Mixta" -> R.drawable.mixta
        "Beeeee!" -> R.drawable.cabra
        "Sangrienta" -> R.drawable.bbq
        "Aberrante" -> R.drawable.aberrante

        "Spaghetti Bolognese" -> R.drawable.bolognese
        "Spaghetti Carbonara" -> R.drawable.carbonarapasta
        "Penne 4 Formaggi" -> R.drawable.pasta4quesos
        "Farfalle Birichine" -> R.drawable.picante

        "Agua Potable Pura" -> R.drawable.water
        "Nuka-Cola" -> R.drawable.nuka
        "Nuka-Cherry" -> R.drawable.cherry
        "Nuka-Cola Quantum" -> R.drawable.quantum

        else -> R.drawable.dripping
    }

    //Añadir a la cesta
    fun onAddToBasket(productoDTO: ProductoDTO, size: SIZE?, cantidad: Int) {
        val lineaPedido = LineaPedidoDTO(cantidad = cantidad, size = size, productoDTO = productoDTO)
        Log.d("HomeViewModel", lineaPedido.toString())
        numeroProductos.value = numeroProductos.value?.plus(cantidad)
        //numeroProductos.value = pedido.value?.lineaPedidos?.sumOf { it.cantidad }
    }
}