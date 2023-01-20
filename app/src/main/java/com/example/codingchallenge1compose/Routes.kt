package com.example.codingchallenge1compose

sealed class Routes(val route: String) {
    object Pantalla1 : Routes("pantalla1")
    object Pantalla2 : Routes("pantalla2/{number1}") {
        fun createRoute(number1: Int) = "pantalla2/$number1"
    }

    object Pantalla3 : Routes("pantalla3/{number1}/{number2}") {
        fun createRoute(number1: Int, number2: Int) = "pantalla3/$number1/$number2"
    }
}
