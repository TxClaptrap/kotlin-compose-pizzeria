package modelo

data class PizzaDTO (
    val id:Int,
    val nombre:String,
    val precio:Double,
    var size:SIZE,
    val listaIngredientes:List<IngredienteDTO>
)