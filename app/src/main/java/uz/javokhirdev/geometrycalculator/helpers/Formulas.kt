package uz.javokhirdev.geometrycalculator.helpers

import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

object Formulas {

    fun perSquare(value: Int): String {
        return (value * 4).round()
    }

    fun areaSquare(value: Int): String {
        return value.pow().round()
    }

    fun perRectangle(firstValue: Int, secondValue: Int): String {
        return ((2 * firstValue) + (2 * secondValue)).round()
    }

    fun areaRectangle(firstValue: Int, secondValue: Int): String {
        return (firstValue * secondValue).round()
    }

    fun perCircle(value: Int): String {
        return (2 * Math.PI * value).round()
    }

    fun areaCircle(value: Int): String {
        return (Math.PI * value.pow()).round()
    }

    fun perTriangle(firstValue: Int, secondValue: Int, thirdValue: Int): String {
        return (firstValue + secondValue + thirdValue).round()
    }

    fun areaTriangle1(firstValue: Int, secondValue: Int): String {
        return (firstValue * secondValue / 2).round()
    }

    fun areaTriangle2(firstValue: Int, secondValue: Int, thirdValue: Int): String {
        return (firstValue * secondValue * sin(thirdValue.toDouble()) / 2).round()
    }

    fun areaTriangle3(firstValue: Int, secondValue: Int, thirdValue: Int): String {
        val per = firstValue + secondValue + thirdValue
        val s = (per) / 2
        return sqrt((s * (s - firstValue) * (s - secondValue) * (s - thirdValue)).toDouble()).round()
    }

    fun areaTrapezoid(firstValue: Int, secondValue: Int, thirdValue: Int): String {
        return (thirdValue * (firstValue + secondValue) / 2).round()
    }

    fun areaParallelogram1(firstValue: Int, secondValue: Int, thirdValue: Int): String {
        return (firstValue * secondValue * sin(thirdValue.toDouble())).round()
    }

    fun areaParallelogram2(firstValue: Int, secondValue: Int): String {
        return (firstValue * secondValue).round()
    }

    fun perPolygon(firstValue: Int, secondValue: Int): String {
        return (firstValue * secondValue).round()
    }

    fun areaPolygon(firstValue: Int, secondValue: Int): String {
        return (firstValue * secondValue.pow() * (cos(Math.PI / firstValue) / sin(Math.PI / firstValue)) / 4).round()
    }

    fun volumeParallelepiped(firstValue: Int, secondValue: Int, thirdValue: Int): String {
        return (firstValue * secondValue * thirdValue).round()
    }

    fun volumeSphere(value: Int): String {
        return (4 * Math.PI * value.pow(3) / 3).round()
    }

    fun volumeCylinder(firstValue: Int, secondValue: Int): String {
        return (Math.PI * firstValue.pow() * secondValue).round()
    }

    fun volumeCone(firstValue: Int, secondValue: Int): String {
        return (Math.PI * firstValue * secondValue.pow() / 3).round()
    }

    fun volumePyramid(firstValue: Int, secondValue: Int): String {
        return (firstValue * secondValue / 3).round()
    }

    fun volumeFrustum(firstValue: Int, secondValue: Int, thirdValue: Int): String {
        return ((firstValue.pow() + firstValue * secondValue + secondValue.pow()) * Math.PI * thirdValue / 3).round()
    }

    fun volumeTorus(firstValue: Int, secondValue: Int): String {
        return (Math.PI.pow(2) * (firstValue + secondValue) * (secondValue - firstValue).pow() / 4).round()
    }

    fun volumeEllipsoid(firstValue: Int, secondValue: Int, thirdValue: Int): String {
        return (Math.PI * firstValue * secondValue * thirdValue * 4 / 3).round()
    }
}