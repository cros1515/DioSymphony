package com.example.diosymphony

import android.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

import com.github.mikephil.charting.formatter.ValueFormatter


// Formateador personalizado para mostrar porcentajes
class PercentValueFormatter(private val total: Float) : ValueFormatter() {
    override fun getPieLabel(value: Float, pieEntry: PieEntry?): String {
        return "${"%.1f".format(value / total * 100)}%"
    }
}


@Composable
fun GraficoTorta(correctas: Int, incorrectas: Int) {
    val total = (correctas + incorrectas).toFloat()

    // Crear las entradas del gráfico
    val entries = listOf(
        PieEntry(correctas.toFloat(), "Correctas"),
        PieEntry(incorrectas.toFloat(), "Incorrectas")
    )

    // Configurar el DataSet
    val dataSet = PieDataSet(entries, "").apply {
        colors = listOf(Color.GREEN, Color.RED)
        valueTextColor = Color.WHITE
        valueTextSize = 14f
    }

    // Configurar los datos del gráfico con el formateador personalizado
    val data = PieData(dataSet).apply {
        setValueFormatter(PercentValueFormatter(total)) // Usar el formateador
    }

    // Renderizar el gráfico en AndroidView
    AndroidView(
        modifier = Modifier.size(300.dp), // Tamaño del gráfico
        factory = { context ->
            PieChart(context).apply {
                this.data = data
                description.isEnabled = false // Ocultar descripción extra
                isDrawHoleEnabled = true // Estilo donut
                setHoleColor(Color.TRANSPARENT)
                setEntryLabelColor(Color.BLACK)
                setUsePercentValues(false) // Los porcentajes se manejan manualmente
                animateY(1000) // Animación de entrada
            }
        }
    )
}




