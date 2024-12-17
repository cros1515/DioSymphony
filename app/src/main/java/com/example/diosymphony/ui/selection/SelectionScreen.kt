package com.example.diosymphony.ui.selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diosymphony.R

@Composable
fun SelectionScreen(onClaveSeleccionada: (String, String) -> Unit) {
    val modalidades = listOf("Modo Clásico", "Modo Cronómetro")
    val claves = listOf(
        Course("Clave de Sol (G3-C5)", R.drawable.nota_g),
        Course("Clave de Sol (C5-C6)", R.drawable.nota_c_c5),
        Course("Clave de Fa (C2-C3)", R.drawable.nota_c_fac2),
        Course("Clave de Fa (C3-C4)", R.drawable.nota_c_fac3),
        Course("Clave de Sol y Clave de Fa (C2-C6)", R.drawable.nota_g_c5),
        Course("Clave de Contralto (C3-C5)", R.drawable.nota_c_do)
    )

    var modalidadSeleccionada by remember { mutableStateOf(modalidades[0]) }
    var claveSeleccionada by remember { mutableStateOf(claves[0].name) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBarSelection()


        SectionTitle(title = "Selecciona la clave musical")
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(claves) { clave ->
                CourseCard(
                    courseName = clave.name,
                    imageRes = clave.imageRes,
                    isSelected = claveSeleccionada == clave.name,
                    onClick = { claveSeleccionada = clave.name }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.fondo), // Reemplazar con tu recurso de imagen
            contentDescription = "Imagen del entrenamiento",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))
        SectionTitle(title = "Selecciona la modalidad")
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(modalidades) { modalidad ->
                SelectableCard(
                    title = modalidad,
                    isSelected = modalidad == modalidadSeleccionada,
                    onClick = { modalidadSeleccionada = modalidad }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        StartButtons(
            onStart = { onClaveSeleccionada(modalidadSeleccionada, claveSeleccionada) }
        )
    }
}

// TopBar
@Composable
fun TopBarSelection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7FAFA))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Volver",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Menú de Selección",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D0F1C)
        )
    }
}

// CourseCard con imagen
@Composable
fun CourseCard(courseName: String, imageRes: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .background(
                if (isSelected) Color(0xFF4F5C94) else Color(0xFFF7FAFA),
                RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = courseName,
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = courseName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Color.White else Color(0xFF0D0F1C),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0D0F1C),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
    )
}

// Botón de Inicio
@Composable
fun StartButtons(onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Button(
            onClick = onStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F5C94))
        ) {
            Text(text = "Iniciar Ejercicios", color = Color.White)
        }
    }
}
@Composable
fun SelectableCard(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .background(
                color = if (isSelected) Color(0xFF4F5C94) else Color(0xFFF7FAFA),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Color.White else Color(0xFF0D0F1C)
        )
    }
}


// Data class para Claves
data class Course(val name: String, val imageRes: Int)