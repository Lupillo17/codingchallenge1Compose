package com.example.codingchallenge1compose

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.codingchallenge1compose.ui.theme.Codingchallenge1ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            Codingchallenge1ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val navControler = rememberNavController()
                    NavHost(
                        navController = navControler,
                        startDestination = Routes.Pantalla1.route
                    ) {
                        composable(Routes.Pantalla1.route) { Screen1(navControler) }
                        composable(
                            Routes.Pantalla2.route,
                            arguments = listOf(navArgument("number1") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            Screen2(
                                navControler,
                                backStackEntry.arguments?.getInt("number1") ?: 0
                            )
                        }
                        composable(
                            Routes.Pantalla3.route,
                            arguments = listOf(
                                navArgument("number1") { type = NavType.IntType },
                                navArgument("number2") { type = NavType.IntType }
                            )
                        ) { navBackStackEntry ->
                            Screen3(
                                navControler,
                                navBackStackEntry.arguments?.getInt("number1") ?: 0,
                                navBackStackEntry.arguments?.getInt("number2") ?: 0
                            )
                        }
                    }
                }
            }
        }
    }
}



