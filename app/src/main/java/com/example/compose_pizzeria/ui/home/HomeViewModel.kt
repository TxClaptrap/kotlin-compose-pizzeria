package com.example.compose_pizzeria.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.compose_pizzeria.R
import com.example.compose_pizzeria.data.ProductoDTO
import com.example.compose_pizzeria.data.TIPO_PRODUCTO
import modelo.IngredienteDTO
import modelo.SIZE

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
        val ingredientesBolognesePasta = listOf(IngredienteDTO(1, "Salsa de tomate", emptyList()), carnePicada, queso)
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
            ProductoDTO(TIPO_PRODUCTO.PIZZA, 1, "Carbonara", 10.0, SIZE.GRANDE, ingredientesCarbonaraPizza),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,2, "4 quesos", 9.5, SIZE.GRANDE, ingredientes4QuesosPizza),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,3, "Pepe", 8.5, SIZE.GRANDE, ingredientesMasPepe),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,4, "Vegetariana", 8.0, SIZE.GRANDE, ingredientesVegetariana),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,5, "Mixta", 9.0, SIZE.GRANDE, ingredientesMixta),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,6, "Cabra", 10.5, SIZE.GRANDE, ingredientesCabra),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,7, "BBQ", 11.0, SIZE.GRANDE, ingredientesBBQ),
            ProductoDTO(TIPO_PRODUCTO.PIZZA,8, "Aberrante", 8.5, SIZE.GRANDE, ingredientesPsicopata),

            // Bebidas
            ProductoDTO(TIPO_PRODUCTO.BEBIDA,9, "Agua", 1.5, SIZE.MEDIANO, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.BEBIDA,10, "Coca Cola", 2.0, SIZE.MEDIANO, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.BEBIDA,11, "Coca Cola Zero", 2.0, SIZE.MEDIANO, emptyList()),
            ProductoDTO(TIPO_PRODUCTO.BEBIDA,12, "Nestea", 2.0, SIZE.MEDIANO, emptyList()),

            // Pastas
            ProductoDTO(TIPO_PRODUCTO.PASTA,13, "Spaghetti Carbonara", 8.0, null, ingredientesCarbonaraPasta),
            ProductoDTO(TIPO_PRODUCTO.PASTA,14, "Spaghetti Bolognese", 7.5, null, ingredientesBolognesePasta),
            ProductoDTO(TIPO_PRODUCTO.PASTA,15, "Penne 4 Formaggi", 9.0, null, ingredientes4QuesosPasta),
            ProductoDTO(TIPO_PRODUCTO.PASTA,16, "Picante", 8.5, null, ingredientesPicantePasta)
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
        "4 quesos" -> R.drawable.pasta4quesos
        //""

        //"Agua" -> R.drawable.agua

        else -> R.drawable.dripping // Si no encuentra el producto
    }

}