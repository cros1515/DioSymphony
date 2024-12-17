package com.example.diosymphony

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diosymphony.ui.theme.DioSymphonyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DioSymphonyTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        NavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}








@Composable
fun ResultadoScreen(
    correctas: Int,
    incorrectas: Int,
    onVolverMenu: () -> Unit
) {
    Scaffold(
        topBar = { TopBarResultados() },
        bottomBar = { BottomBarVolverMenu(onVolverMenu) }, // Botón en la BottomBar
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Sección de estadísticas principales
            EstadisticasSection()
            Spacer(modifier = Modifier.height(24.dp))

            // Gráfico de torta centrado
            GraficoTortaSection(correctas, incorrectas)
            Spacer(modifier = Modifier.height(16.dp))

            // Cuadros de respuestas correctas e incorrectas
            EstadisticasCuadros(correctas, incorrectas)
            Spacer(modifier = Modifier.height(16.dp))

            // Cuadros adicionales: Ejercicios Realizados y Tiempo Promedio
            EstadisticasAdicionales(correctas, incorrectas)
        }
    }
}

// TopBar personalizado
@Composable
fun TopBarResultados() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7FAFA))
            .padding(vertical = 10.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resultados",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

// Sección de mensaje principal
@Composable
fun EstadisticasSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "¡Ejercicios Finalizados!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

// Gráfico de torta
@Composable
fun GraficoTortaSection(correctas: Int, incorrectas: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        GraficoTorta(correctas, incorrectas)
    }
}

// Cuadros de respuestas correctas e incorrectas
@Composable
fun EstadisticasCuadros(correctas: Int, incorrectas: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        EstadisticaItem(titulo = "Correctas", valor = correctas.toString())
        EstadisticaItem(titulo = "Incorrectas", valor = incorrectas.toString())
    }
}

// Cuadros adicionales: Ejercicios Realizados y Tiempo promedio
@Composable
fun EstadisticasAdicionales(correctas: Int, incorrectas: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        EstadisticaItem(
            titulo = "Ejercicios Realizados",
            valor = (correctas + incorrectas).toString()
        )
        EstadisticaItem(titulo = "Tiempo Promedio", valor = "10s")
    }
}

// Cuadro individual
@Composable
fun EstadisticaItem(titulo: String, valor: String) {
    Column(
        modifier = Modifier
            .width(158.dp)
            .height(100.dp)
            .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titulo,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Text(
            text = valor,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

// Botón inferior
@Composable
fun BottomBarVolverMenu(onVolverMenu: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = onVolverMenu,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F5C94))
        ) {
            Text(
                text = "Volver al Menú",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}