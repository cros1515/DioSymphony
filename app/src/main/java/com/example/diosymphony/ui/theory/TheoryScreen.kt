package com.example.diosymphony.ui.theory

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diosymphony.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheoryScreen(navController: NavController) {
    val categories = listOf("Notas en Clave de Sol", "Notas en Clave de Fa", "Notas en Clave de Contralto")
    var selectedCategory by remember { mutableStateOf(categories[0]) } // Selección inicial

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Encabezado
        TopAppBar(
            title = {
                Text(
                    text = "Teoría",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D0F1C)
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) { // Acción para volver atrás
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF7FAFA))
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Título
        Text(
            text = "Elige una categoría",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D0F1C),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Lista de categorías
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryItem(
                    name = category,
                    isSelected = category == selectedCategory,
                    onClick = { selectedCategory = category }
                )
            }
        }

        // Imagen de ejemplo
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.notas1), // Reemplazar con tu recurso de imagen
            contentDescription = "Imagen Teoría",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Botones de acción
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    when (selectedCategory) {
                        "Notas en Clave de Sol" -> navController.navigate("manual_clave_sol")
                        "Notas en Clave de Fa" -> navController.navigate("manual_clave_fa")
                        "Notas en Clave de Contralto" -> navController.navigate("manual_clave_contralto")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F5C94))
            ) {
                Text(text = "Empezar", color = Color.White, fontWeight = FontWeight.Bold)
            }


            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { /* Acción para explicación */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF14A487))
            ) {
                Text(text = "Explicación", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Mensaje de ayuda
        Text(
            text = "Haz clic en Empezar para iniciar con las lecciones.",
            fontSize = 16.sp,
            color = Color(0xFF0D0F1C),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun CategoryItem(name: String, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor = if (isSelected) Color(0xFF4F5C94) else Color(0xFFE5E8EB)
    val textColor = if (isSelected) Color.White else Color(0xFF0D0F1C)
    val backgroundColor = if (isSelected) Color(0xFF4F5C94) else Color(0xFFF7FAFA)

    Box(
        modifier = Modifier
            .width(150.dp)
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualClaveSolScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text("Manual: Clave de Sol", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF7FAFA))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen Principal
        Image(
            painter = painterResource(id = R.drawable.solmanual), // Imagen del pentagrama con clave de sol
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Introducción
        Text(
            text = "La clave de sol es utilizada para las notas agudas y es una de las más comunes en la música. " +
                    "Se coloca al inicio del pentagrama, donde la segunda línea corresponde a la nota \"Sol\".",
            fontSize = 18.sp,
            lineHeight = 26.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tabla de notas en el pentagrama
        Text(
            text = "🎵 Notas en el Pentagrama",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.notas1), // Imagen que muestra todas las notas en clave de sol
            contentDescription = "Notas en Clave de Sol",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Explicación de las notas
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            NotaExplicacion("Mi", "Primera línea inferior del pentagrama.")
            NotaExplicacion("Sol", "Segunda línea del pentagrama (línea de la clave).")
            NotaExplicacion("Si", "Tercera línea del pentagrama.")
            NotaExplicacion("Re", "Cuarta línea del pentagrama.")
            NotaExplicacion("Fa", "Quinta línea del pentagrama.")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Curiosidades
        Text(
            text = "🌟 Curiosidades",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = """
                • La clave de sol es la más utilizada en partituras para instrumentos como el violín, flauta, piano (mano derecha) y guitarra.
                • El símbolo de la clave de sol tiene origen en la notación musical medieval.
                • Es ideal para representar melodías de tono alto.
            """.trimIndent(),
            fontSize = 16.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sección interactiva (botones o ejemplos prácticos)
        Text(
            text = "🔍 Practica identificando notas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listOf("Mi", "Sol", "Si", "Re", "Fa")) { nota ->
                NotaPracticaItem(nota) {
                    // Acción para la nota seleccionada
                    println("Practicaste: $nota")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Finalizar
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F5C94))
        ) {
            Text("Finalizar", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

// Composable para explicar cada nota
@Composable
fun NotaExplicacion(nota: String, descripcion: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF4F5C94), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = nota, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Text(text = descripcion, fontSize = 16.sp, color = Color.DarkGray)
    }
}

// Composable para las prácticas de notas
@Composable
fun NotaPracticaItem(nota: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF7FAFA))
            .border(2.dp, Color(0xFF4F5C94), RoundedCornerShape(12.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = nota, color = Color(0xFF4F5C94), fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualClaveFaScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text("Manual: Clave de Fa", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF7FAFA))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen principal
        Image(
            painter = painterResource(id = R.drawable.famanual), // Imagen con clave de fa
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Introducción
        Text(
            text = "La clave de fa es utilizada para representar notas graves y es comúnmente usada en instrumentos como el contrabajo, el fagot y el piano (mano izquierda). " +
                    "Se coloca al inicio del pentagrama, donde la cuarta línea corresponde a la nota \"Fa\".",
            fontSize = 18.sp,
            lineHeight = 26.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tabla de notas
        Text(
            text = "🎵 Notas en el Pentagrama",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.notas1), // Imagen que muestra notas clave de fa
            contentDescription = "Notas en Clave de Fa",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Explicación de las notas
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            NotaExplicacion("Sol", "Primera línea inferior del pentagrama.")
            NotaExplicacion("Si", "Segunda línea del pentagrama.")
            NotaExplicacion("Re", "Tercera línea del pentagrama.")
            NotaExplicacion("Fa", "Cuarta línea del pentagrama (línea de la clave).")
            NotaExplicacion("La", "Quinta línea del pentagrama.")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Curiosidades
        Text(
            text = "🌟 Curiosidades",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = """
                • La clave de fa también es conocida como clave de bajo.
                • En partituras de piano, la mano izquierda lee principalmente en clave de fa.
                • La clave de fa aparece en la música coral, asignada comúnmente a las voces más graves como los bajos.
            """.trimIndent(),
            fontSize = 16.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sección interactiva
        Text(
            text = "🔍 Practica identificando notas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listOf("Sol", "Si", "Re", "Fa", "La")) { nota ->
                NotaPracticaItem(nota) {
                    println("Practicaste: $nota")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Finalizar
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F5C94))
        ) {
            Text("Finalizar", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualClaveContraltoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text("Manual: Clave de Do", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF7FAFA))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen principal
        Image(
            painter = painterResource(id = R.drawable.domanual), // Imagen clave de do
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Introducción
        Text(
            text = "La clave de do se utiliza para notas de rango medio y es común en instrumentos como la viola. " +
                    "Se coloca en diferentes líneas del pentagrama, pero la más utilizada es la tercera, conocida como clave de contralto.",
            fontSize = 18.sp,
            lineHeight = 26.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tabla de notas
        Text(
            text = "🎵 Notas en el Pentagrama",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.notas1), // Imagen que muestra notas clave de do
            contentDescription = "Notas en Clave de Do",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Explicación de las notas
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            NotaExplicacion("Mi", "Primera línea inferior del pentagrama.")
            NotaExplicacion("Sol", "Segunda línea del pentagrama.")
            NotaExplicacion("Do", "Tercera línea del pentagrama (línea de la clave).")
            NotaExplicacion("Mi", "Cuarta línea del pentagrama.")
            NotaExplicacion("Sol", "Quinta línea del pentagrama.")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Curiosidades
        Text(
            text = "🌟 Curiosidades",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = """
                • La clave de do es utilizada principalmente en la música de cámara.
                • Es muy común en partituras para la viola, el trombón alto y el fagot.
                • El símbolo de la clave de do evoluciona de la notación medieval.
            """.trimIndent(),
            fontSize = 16.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sección interactiva
        Text(
            text = "🔍 Practica identificando notas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listOf("Mi", "Sol", "Do", "Mi", "Sol")) { nota ->
                NotaPracticaItem(nota) {
                    println("Practicaste: $nota")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Finalizar
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F5C94))
        ) {
            Text("Finalizar", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}





