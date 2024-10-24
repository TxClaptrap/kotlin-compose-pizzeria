package modelo

data class PastaDTO (
    val id:Int,
    val nombre:String,
    val precio:Double,
    val listaIngredientes:List<IngredienteDTO>
)