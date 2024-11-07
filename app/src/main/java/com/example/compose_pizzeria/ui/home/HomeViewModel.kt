package com.example.compose_pizzeria.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.ProductoDTO
import com.example.compose_pizzeria.data.TIPO_PRODUCTO
import modelo.IngredienteDTO

class HomeViewModel {
    //private val lista: List<ProductoDTO> = listOf()
    //val productos = MutableLiveData(lista)
    //A Adri le gusta más así:
    val productos: MutableLiveData<List<ProductoDTO>> = MutableLiveData(listOf())
    var listProductos: List<ProductoDTO> = emptyList()

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
        val piña = IngredienteDTO(14, "Piña", emptyList())

        // Listas de ingredientes
        val ingredientesCarbonaraPizza = listOf(queso, bacon, champiñones, nata)
        val ingredientes4QuesosPizza = listOf(
            IngredienteDTO(15, "Queso mozzarella", emptyList()),
            IngredienteDTO(16, "Queso gorgonzola", emptyList()),
            IngredienteDTO(17, "Queso parmesano", emptyList()),
            IngredienteDTO(18, "Queso cheddar", emptyList()),
            tomate
        )
        val ingredientesMasPepe = listOf(queso, peperoni, tomate)
        val ingredientesVegetariana = listOf(queso, pimiento, cebolla, aceitunas, tomate)
        val ingredientesMixta = listOf(queso, jamonYork, tomate)
        val ingredientesCabra = listOf(quesoDeCabra, queso, tomate)
        val ingredientesBBQ = listOf(carnePicada, bacon, salsaBarbacoa, queso)
        val ingredientesPsicopata = listOf(queso, jamonYork, piña, tomate)

        // Ingredientes para pastas
        val ingredientesCarbonaraPasta = listOf(nata, bacon, queso)
        val ingredientesBolognesePasta = listOf(IngredienteDTO(19, "Salsa de tomate", emptyList()), carnePicada, queso)
        val ingredientes4QuesosPasta = listOf(
            IngredienteDTO(15, "Queso mozzarella", emptyList()),
            IngredienteDTO(16, "Queso gorgonzola", emptyList()),
            IngredienteDTO(17, "Queso parmesano", emptyList()),
            IngredienteDTO(18, "Queso cheddar", emptyList())
        )
        val ingredientesPicantePasta = listOf(
            IngredienteDTO(20, "Salsa picante", emptyList()),
            carnePicada,
            IngredienteDTO(21, "Jalapeños", emptyList())
        )

        // Lista de productos
        listProductos = listOf(
            // Pizzas
            ProductoDTO(TIPO_PRODUCTO.PIZZA, 1, "Carbonara", 10.0,  ingredientesCarbonaraPizza),
            ProductoDTO(2, "4 quesos", 9.5, TIPO_PRODUCTO.PIZZA, ingredientes4QuesosPizza),
            ProductoDTO(3, "Más Pepe", 8.5, TIPO_PRODUCTO.PIZZA, ingredientesMasPepe),
            ProductoDTO(4, "Vegetariana", 8.0, TIPO_PRODUCTO.PIZZA, ingredientesVegetariana),
            ProductoDTO(5, "Mixta", 9.0, TIPO_PRODUCTO.PIZZA, ingredientesMixta),
            ProductoDTO(6, "Cabra", 10.5, TIPO_PRODUCTO.PIZZA, ingredientesCabra),
            ProductoDTO(7, "BBQ", 11.0, TIPO_PRODUCTO.PIZZA, ingredientesBBQ),
            ProductoDTO(8, "Psicópata", 8.5, TIPO_PRODUCTO.PIZZA, ingredientesPsicopata),

            // Bebidas
            ProductoDTO(9, "Agua", 1.5, TIPO_PRODUCTO.BEBIDA, emptyList()),
            ProductoDTO(10, "Coca Cola", 2.0, TIPO_PRODUCTO.BEBIDA, emptyList()),
            ProductoDTO(11, "Coca Cola Zero", 2.0, TIPO_PRODUCTO.BEBIDA, emptyList()),
            ProductoDTO(12, "Nestea", 2.0, TIPO_PRODUCTO.BEBIDA, emptyList()),

            // Pastas
            ProductoDTO(13, "Carbonara", 8.0, TIPO_PRODUCTO.PASTA, ingredientesCarbonaraPasta),
            ProductoDTO(14, "Bolognese", 7.5, TIPO_PRODUCTO.PASTA, ingredientesBolognesePasta),
            ProductoDTO(15, "4 Quesos", 9.0, TIPO_PRODUCTO.PASTA, ingredientes4QuesosPasta),
            ProductoDTO(16, "Picante", 8.5, TIPO_PRODUCTO.PASTA, ingredientesPicantePasta)
        )
    }


    fun obtenerImagen(nombre: String) = when(nombre) {
        "4 quesos" -> R.drawable.quesos4
        "bbq" -> R.drawable.bbq
        else -> R.drawable.dripping // Si no encuentra el producto
    }

}