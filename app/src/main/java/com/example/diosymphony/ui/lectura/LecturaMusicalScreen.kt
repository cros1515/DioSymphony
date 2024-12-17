package com.example.diosymphony.ui.lectura

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue

@Composable
fun LecturaMusicalScreen(
    modalidad: String,
    claveSeleccionada: String,
    navController: NavHostController,
    viewModel: LecturaMusicalViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val imagenes by viewModel.imagenes.collectAsState()
    val indiceActual by viewModel.indiceActual.collectAsState()
    val mensajeRespuesta by viewModel.mensajeRespuesta.collectAsState()
    val respuestasCorrectas by viewModel.respuestasCorrectas.collectAsState()
    val respuestasIncorrectas by viewModel.respuestasIncorrectas.collectAsState()

    // Cronómetro
    var tiempoRestante = remember { mutableStateOf(30) }
    LaunchedEffect(modalidad) {
        if (modalidad == "Modo Cronómetro") {
            while (tiempoRestante.value > 0) {
                kotlinx.coroutines.delay(1000L)
                tiempoRestante.value--
            }
            navController.navigate("resultado/$respuestasCorrectas/$respuestasIncorrectas")
        }
    }

    LaunchedEffect(claveSeleccionada, modalidad) {
        viewModel.cargarDatosPorClave(claveSeleccionada, modalidad)
    }

    if (imagenes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Cargando ejercicios...", fontSize = 18.sp)
        }
    } else if (modalidad == "Modo Clásico" && indiceActual >= imagenes.size) {
        LaunchedEffect(Unit) {
            navController.navigate("resultado/$respuestasCorrectas/$respuestasIncorrectas")
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (modalidad == "Modo Clásico") {
                Text(
                    text = "Ejercicio ${indiceActual + 1}/${imagenes.size}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
            } else if (modalidad == "Modo Cronómetro") {
                Text(
                    text = "Tiempo restante: ${tiempoRestante.value} s",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp),
                    color = if (tiempoRestante.value <= 10) Color.Red else Color.Black
                )
            }

            Image(
                painter = painterResource(id = imagenes[indiceActual].imagenResId),
                contentDescription = "Nota musical",
                modifier = Modifier.size(200.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                listOf("C", "D", "E", "F", "G", "A", "B").forEach { nota ->
                    Button(
                        onClick = { viewModel.verificarRespuesta(nota) },
                        modifier = Modifier.size(50.dp)
                    ) {
                        Text(text = nota)
                    }
                }
            }

            // Mostrar íconos en lugar del texto
            mensajeRespuesta?.let { mensaje ->
                val icono = if (mensaje == "Correcto") Icons.Default.CheckCircle else Icons.Default.Close
                val color = if (mensaje == "Correcto") Color.Green else Color.Red

                Icon(
                    imageVector = icono,
                    contentDescription = mensaje,
                    tint = color,
                    modifier = Modifier
                        .size(100.dp) // Tamaño considerable
                        .padding(16.dp)
                )
            }
        }
    }
}