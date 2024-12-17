package com.example.diosymphony.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diosymphony.R

@Composable
fun Home(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ajuste para llenar toda la pantalla
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.applog),
                    contentDescription = "Image"
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Dio",
                        fontSize = 60.sp,
                        color = Color(0xFFE5E8EB),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "SYMPHONY",
                        fontSize = 32.sp,
                        color = Color(0xFF14A487),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton("TEORÍA") {
                    navController.navigate("theory")
                    // Navegar a TEORÍA (deberías implementar la pantalla correspondiente)
                }
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton("ENTRENAMIENTO") {
                    navController.navigate("selection") // Navegar al menú de selección
                }
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton("DICCIONARIO") {
                    navController.navigate("diccionario")

                    // Navegar a DICCIONARIO (deberías implementar la pantalla correspondiente)
                }
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton("AJUSTES") {
                    // Navegar a AJUSTES (deberías implementar la pantalla correspondiente)
                }
            }
        }
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(332.dp)
            .height(81.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}