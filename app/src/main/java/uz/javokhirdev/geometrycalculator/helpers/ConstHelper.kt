package uz.javokhirdev.geometrycalculator.helpers

import uz.javokhirdev.geometrycalculator.R
import uz.javokhirdev.geometrycalculator.data.ShapeResponse

object ConstHelper {

    fun get2DShapes(): ArrayList<ShapeResponse> {
        val shapes = ArrayList<ShapeResponse>()
        shapes.add(ShapeResponse(1, R.drawable.ic_menu_square))
        shapes.add(ShapeResponse(2, R.drawable.ic_menu_rectangle))
        shapes.add(ShapeResponse(3, R.drawable.ic_menu_circle))
        shapes.add(ShapeResponse(4, R.drawable.ic_menu_triangle))
        shapes.add(ShapeResponse(5, R.drawable.ic_menu_trapezoid))
        shapes.add(ShapeResponse(6, R.drawable.ic_menu_parallelogram))
        shapes.add(ShapeResponse(7, R.drawable.ic_menu_polygon))
        return shapes
    }

    fun get3DShapes(): ArrayList<ShapeResponse> {
        val shapes = ArrayList<ShapeResponse>()
        shapes.add(ShapeResponse(1, R.drawable.ic_menu_parallelepiped))
        shapes.add(ShapeResponse(2, R.drawable.ic_menu_sphere))
        shapes.add(ShapeResponse(3, R.drawable.ic_menu_cylinder))
        shapes.add(ShapeResponse(4, R.drawable.ic_menu_cone))
        shapes.add(ShapeResponse(5, R.drawable.ic_menu_pyramid))
        shapes.add(ShapeResponse(6, R.drawable.ic_menu_frustum))
        shapes.add(ShapeResponse(7, R.drawable.ic_menu_torus))
        shapes.add(ShapeResponse(8, R.drawable.ic_menu_ellipsoid))
        return shapes
    }
}