package com.example.diosymphony.ui.dictionary



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    navController: NavController,
    viewModel: DictionaryViewModel = viewModel()
) {
    val terms by viewModel.terms.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text("Diccionario Musical", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF7FAFA))
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Categorías de filtro
        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listOf("Todos", "Notas", "Acordes", "Dinámicas")) { category ->
                FilterChip(
                    category = category,
                    isSelected = selectedCategory == category,
                    onClick = { viewModel.loadTerms(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de términos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(terms) { term ->
                DictionaryItem(
                    termName = term.name,
                    termImage = term.imageRes,
                    termDescription = term.description
                )
            }
        }
    }
}

@Composable
fun FilterChip(category: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFF4F5C94) else Color(0xFFE5E8EB),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Composable
fun DictionaryItem(termName: String, termImage: Int?, termDescription: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7FAFA), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        // Imagen opcional
        if (termImage != null) {
            Image(
                painter = painterResource(id = termImage),
                contentDescription = termName,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Nombre y descripción
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = termName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D0F1C)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = termDescription,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
