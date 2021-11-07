package uz.javokhirdev.geometrycalculator.helpers

data class ShapeResponse(val id: Int, val icon: Int)

data class ShapeDetailResponse(
    val id: Int,
    val name: String,
    val icon: Int,
    val isGeometric: Boolean
)

