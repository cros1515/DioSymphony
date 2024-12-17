package com.example.diosymphony

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diosymphony.ui.dictionary.DictionaryScreen
import com.example.diosymphony.ui.home.Home
import com.example.diosymphony.ui.lectura.LecturaMusicalScreen
import com.example.diosymphony.ui.selection.SelectionScreen
import com.example.diosymphony.ui.theory.ManualClaveContraltoScreen
import com.example.diosymphony.ui.theory.ManualClaveFaScreen
import com.example.diosymphony.ui.theory.ManualClaveSolScreen
import com.example.diosymphony.ui.theory.TheoryScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home", // Cambiar la pantalla inicial a "home"
        modifier = modifier
    ) {
        // Pantalla de Home
        composable("home") {
            Home(navController = navController)
        }

        // Pantalla de selección (Entrenamiento)
        composable("selection") {
            SelectionScreen  { modalidad, claveSeleccionada ->
                navController.navigate("lectura/$modalidad/$claveSeleccionada")
            }
        }

        // Pantalla de ejercicios
        composable(
            route = "lectura/{modalidad}/{claveSeleccionada}",
            arguments = listOf(
                navArgument("modalidad") { type = NavType.StringType },
                navArgument("claveSeleccionada") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val modalidad = backStackEntry.arguments?.getString("modalidad") ?: "Modo Clásico"
            val claveSeleccionada = backStackEntry.arguments?.getString("claveSeleccionada") ?: ""
            LecturaMusicalScreen(
                modalidad = modalidad,
                claveSeleccionada = claveSeleccionada,
                navController = navController
            )
        }

        // Pantalla de resultados
        composable(
            route = "resultado/{correctas}/{incorrectas}",
            arguments = listOf(
                navArgument("correctas") { type = NavType.IntType },
                navArgument("incorrectas") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val correctas = backStackEntry.arguments?.getInt("correctas") ?: 0
            val incorrectas = backStackEntry.arguments?.getInt("incorrectas") ?: 0
            ResultadoScreen(
                correctas = correctas,
                incorrectas = incorrectas
            ) {
                navController.navigate("selection") { // Regresar a SelectionScreen
                    popUpTo("selection") { inclusive = true }
                }
            }
        }

        composable("theory") {
            TheoryScreen(navController = navController)
        }
        composable("manual_clave_sol") {
            ManualClaveSolScreen(navController = navController)
        }

        composable("manual_clave_fa") {
            ManualClaveFaScreen(navController = navController)
        }

        composable("manual_clave_contralto") {
            ManualClaveContraltoScreen(navController = navController)
        }
        composable("diccionario") {
            DictionaryScreen(navController = navController)
        }





    }
}
