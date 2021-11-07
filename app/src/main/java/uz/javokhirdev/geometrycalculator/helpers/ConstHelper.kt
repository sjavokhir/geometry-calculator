package uz.javokhirdev.geometrycalculator.helpers

import uz.javokhirdev.geometrycalculator.R

object ConstHelper {

    fun get2DShapes(): ArrayList<ShapeResponse> {
        val shapes = ArrayList<ShapeResponse>()
        shapes.add(ShapeResponse(1, R.drawable.ic_square))
        shapes.add(ShapeResponse(2, R.drawable.ic_rectangle))
        shapes.add(ShapeResponse(3, R.drawable.ic_circle))
        shapes.add(ShapeResponse(4, R.drawable.ic_triangle_1))
        shapes.add(ShapeResponse(5, R.drawable.ic_triangle_2))
        shapes.add(ShapeResponse(6, R.drawable.ic_triangle_3))
        shapes.add(ShapeResponse(7, R.drawable.ic_trapezoid))
        shapes.add(ShapeResponse(8, R.drawable.ic_parallelogram_1))
        shapes.add(ShapeResponse(9, R.drawable.ic_parallelogram_2))
        shapes.add(ShapeResponse(10, R.drawable.ic_regular_polygon))
        return shapes
    }

    fun get3DShapes(): ArrayList<ShapeResponse> {
        val shapes = ArrayList<ShapeResponse>()
        shapes.add(ShapeResponse(11, R.drawable.ic_parallelepiped))
        shapes.add(ShapeResponse(12, R.drawable.ic_sphere))
        shapes.add(ShapeResponse(13, R.drawable.ic_cylinder))
        shapes.add(ShapeResponse(14, R.drawable.ic_cone))
        shapes.add(ShapeResponse(15, R.drawable.ic_pyramid))
        shapes.add(ShapeResponse(16, R.drawable.ic_frustum))
        shapes.add(ShapeResponse(17, R.drawable.ic_torus))
        shapes.add(ShapeResponse(18, R.drawable.ic_ellipsoid))
        return shapes
    }

    private fun getAllShapes(): ArrayList<ShapeDetailResponse> {
        val shapes = ArrayList<ShapeDetailResponse>()
        shapes.add(ShapeDetailResponse(1, "Square", R.drawable.ic_square, true))
        shapes.add(ShapeDetailResponse(2, "Rectangle", R.drawable.ic_rectangle, true))
        shapes.add(ShapeDetailResponse(3, "Cicrle", R.drawable.ic_circle, true))
        shapes.add(ShapeDetailResponse(4, "Triangle", R.drawable.ic_triangle_1, true))
        shapes.add(ShapeDetailResponse(5, "Triangle", R.drawable.ic_triangle_2, true))
        shapes.add(ShapeDetailResponse(6, "Triangle", R.drawable.ic_triangle_3, true))
        shapes.add(ShapeDetailResponse(7, "Trapezoid", R.drawable.ic_trapezoid, true))
        shapes.add(ShapeDetailResponse(8, "Parallelogram", R.drawable.ic_parallelogram_1, true))
        shapes.add(ShapeDetailResponse(9, "Parallelogram", R.drawable.ic_parallelogram_2, true))
        shapes.add(ShapeDetailResponse(10, "Polygon", R.drawable.ic_regular_polygon, true))
        shapes.add(ShapeDetailResponse(11, "Parallelepiped", R.drawable.ic_parallelepiped, false))
        shapes.add(ShapeDetailResponse(12, "Sphere", R.drawable.ic_sphere, false))
        shapes.add(ShapeDetailResponse(13, "Cylinder", R.drawable.ic_cylinder, false))
        shapes.add(ShapeDetailResponse(14, "Cone", R.drawable.ic_cone, false))
        shapes.add(ShapeDetailResponse(15, "Pyramid", R.drawable.ic_pyramid, false))
        shapes.add(ShapeDetailResponse(16, "Frustum", R.drawable.ic_frustum, false))
        shapes.add(ShapeDetailResponse(17, "Torus", R.drawable.ic_torus, false))
        shapes.add(ShapeDetailResponse(18, "Ellipsoid", R.drawable.ic_ellipsoid, false))
        return shapes
    }

    fun getShapeDetail(id: Int): ShapeDetailResponse? {
        return getAllShapes().find { it.id == id }
    }
}