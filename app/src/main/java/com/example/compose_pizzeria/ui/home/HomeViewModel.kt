package com.example.compose_pizzeria.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.ProductoDTO
import com.example.compose_pizzeria.data.TIPO_PRODUCTO
import modelo.IngredienteDTO
import modelo.LineaPedidoDTO
import modelo.PedidoDTO
import modelo.SIZE

class HomeViewModel {
    //private val lista: List<ProductoDTO> = listOf()
    //val productos = MutableLiveData(lista)
    //O si te gusta más así...:
    val productos: MutableLiveData<List<ProductoDTO>> = MutableLiveData(listOf())
    var listProductos: List<ProductoDTO> = emptyList()
    val pedido: MutableLiveData<PedidoDTO> = MutableLiveData(PedidoDTO())
    val numeroProductos: MutableLiveData<Int> = MutableLiveData(0)

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        // Ingredientes comunes
        val queso = IngredienteDTO(1, "Queso", emptyList())
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

        // Listas de ingredientes
        val ingredientesCarbonaraPizza = listOf(queso, bacon, champiñones, nata)
        val ingredientes4Quesos = listOf(
            IngredienteDTO(15, "Mozzarella", emptyList()),
            IngredienteDTO(16, "Gorgonzola", emptyList()),
            IngredienteDTO(17, "Parmesano", emptyList()),
            IngredienteDTO(18, "Cheddar", emptyList()),
            tomate
        )
        val ingredientesMasPepe = listOf(queso, peperoni, tomate)
        val ingredientesVegetariana = listOf(queso, pimiento, cebolla, aceitunas, tomate)
        val ingredientesMixta = listOf(queso, jamonYork, tomate)
        val ingredientesCabra = listOf(quesoDeCabra, queso, tomate)
        val ingredientesBBQ = listOf(carnePicada, bacon, salsaBarbacoa, queso)
        val ingredientesPsicopata = listOf(queso, jamonYork, pinya, tomate)

        // Ingredientes para pastas
        val ingredientesCarbonaraPasta = listOf(nata, bacon, queso)
        val ingredientesBolognesePasta = listOf(IngredienteDTO(1, "Salsa de tomate", emptyList()), carnePicada, queso)
        val ingredientesPicantePasta = listOf(
            IngredienteDTO(20, "Salsa picante", emptyList()),
            carnePicada,
            IngredienteDTO(21, "Jalapeños", emptyList())
        )

        // Lista de productos
        listProductos = listOf(
            // Pizzas
            ProductoDTO(TIPO_PRODUCTO.PIZZA, 1, "Carbonara", 10.0, null, ingredientesCarbonaraPizza),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,2, "4 quesos", 9.5, null, ingredientes4Quesos),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,3, "Pepe", 8.5, null, ingredientesMasPepe),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,4, "Vegetariana", 8.0, null, ingredientesVegetariana),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,5, "Mixta", 9.0, null, ingredientesMixta),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,6, "Cabra", 10.5, null, ingredientesCabra),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,7, "BBQ", 11.0, null, ingredientesBBQ),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,8, "Aberrante", 8.5, null, ingredientesPsicopata),

            // Pastas
            ProductoDTO(TIPO_PRODUCTO.PASTA,13, "Spaghetti Carbonara", 8.0, null, ingredientesCarbonaraPasta),
            ProductoDTO(TIPO_PRODUCTO.PASTA,14, "Spaghetti Bolognese", 7.5, null, ingredientesBolognesePasta),
            ProductoDTO(TIPO_PRODUCTO.PASTA,15, "Penne 4 Formaggi", 9.0, null, ingredientes4Quesos),
            ProductoDTO(TIPO_PRODUCTO.PASTA,16, "Farfalle Birichine", 8.5, null, ingredientesPicantePasta),

                    // Bebidas
            ProductoDTO(TIPO_PRODUCTO.BEBIDA,9, "Agua Potable Pura", 1.5, null, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.BEBIDA,10, "Nuka-Cola", 2.0, null, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.BEBIDA,11, "Nuka-Cherry", 2.0, null, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.BEBIDA,12, "Nuka-Cola Quantum", 3.0, null, emptyList())
        )
    }


    fun obtenerImagen(nombre: String) = when(nombre) {
        "Carbonara" -> R.drawable.carbonara
        "4 quesos" -> R.drawable.quesos4
        "Pepe" -> R.drawable.pepe
        "Vegetariana" -> R.drawable.vegetariana
        "Mixta" -> R.drawable.mixta
        "Cabra" -> R.drawable.cabra
        "BBQ" -> R.drawable.bbq
        "Aberrante" -> R.drawable.aberrante

        "Spaghetti Bolognese" -> R.drawable.bolognese
        "Spaghetti Carbonara" -> R.drawable.carbonarapasta
        "Penne 4 Formaggi" -> R.drawable.pasta4quesos
        "Farfalle Birichine" -> R.drawable.picante
        //""

        "Agua Potable Pura" -> R.drawable.water
        "Nuka-Cola" -> R.drawable.nuka
        "Nuka-Cherry" -> R.drawable.cherry
        "Nuka-Cola Quantum" -> R.drawable.quantum

        else -> R.drawable.dripping // Si no encuentra el producto
    }

    fun onAddToBasket(productoDTO: ProductoDTO, size: SIZE?, cantidad: Int) {
        val lineaPedido = LineaPedidoDTO(cantidad = cantidad, size = size, productoDTO = productoDTO)
        Log.d("HomeViewModel", lineaPedido.toString())
        numeroProductos.value = numeroProductos.value?.plus(cantidad)
        //numeroProductos.value = pedido.value?.lineaPedidos?.sumOf { it.cantidad }
    }
}