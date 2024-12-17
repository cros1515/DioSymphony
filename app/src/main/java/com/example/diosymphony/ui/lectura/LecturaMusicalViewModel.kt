package com.example.diosymphony.ui.lectura

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope




import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import android.os.Vibrator
import android.os.VibratorManager
import com.example.diosymphony.R

import kotlinx.coroutines.launch




class LecturaMusicalViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext

    private val _imagenes = MutableStateFlow(emptyList<Nota>())
    val imagenes: StateFlow<List<Nota>> = _imagenes

    private val _indiceActual = MutableStateFlow(0)
    val indiceActual: StateFlow<Int> = _indiceActual

    private val _mensajeRespuesta = MutableStateFlow<String?>(null)
    val mensajeRespuesta: StateFlow<String?> = _mensajeRespuesta

    private val _respuestasCorrectas = MutableStateFlow(0)
    val respuestasCorrectas: StateFlow<Int> = _respuestasCorrectas

    private val _respuestasIncorrectas = MutableStateFlow(0)
    val respuestasIncorrectas: StateFlow<Int> = _respuestasIncorrectas

    private var mediaPlayer: MediaPlayer? = null // MediaPlayer para manejar sonidos

    fun cargarDatosPorClave(claveSeleccionada: String,modalidad:String) {
        val datosPorClave = when (claveSeleccionada) {
            "Clave de Sol (G3-C5)" -> listOf(
                Nota(R.drawable.nota_g, "G"),
                Nota(R.drawable.nota_a, "A"),
                Nota(R.drawable.nota_b, "B"),
                Nota(R.drawable.nota_c, "C"),
                Nota(R.drawable.nota_d, "D"),
                Nota(R.drawable.nota_e, "E"),
                Nota(R.drawable.nota_f, "F")
            )
            "Clave de Sol (C5-C6)" -> listOf(
                Nota(R.drawable.nota_c_c5, "C"),
                Nota(R.drawable.nota_d_c5, "D"),
                Nota(R.drawable.nota_e_c5, "E"),
                Nota(R.drawable.nota_f_c5, "F"),
                Nota(R.drawable.nota_g_c5, "G"),
                Nota(R.drawable.nota_a_c5, "A"),
                Nota(R.drawable.nota_b_c5, "B")
            )
            "Clave de Fa (C2-C3)" -> listOf(
                Nota(R.drawable.nota_c_fac2, "C"),
                Nota(R.drawable.nota_d_fac2, "D"),
                Nota(R.drawable.nota_e_fac2, "E"),
                Nota(R.drawable.nota_f_fac2, "F"),
                Nota(R.drawable.nota_g_fac2, "G"),
                Nota(R.drawable.nota_a_fac2, "A"),
                Nota(R.drawable.nota_b_fac2, "B")
            )
            "Clave de Fa (C3-C4)" -> listOf(
                Nota(R.drawable.nota_c_fac3, "C"),
                Nota(R.drawable.nota_d_fac3, "D"),
                Nota(R.drawable.nota_e_fac3, "E"),
                Nota(R.drawable.nota_f_fac3, "F"),
                Nota(R.drawable.nota_g_fac3, "G"),
                Nota(R.drawable.nota_a_fac3, "A"),
                Nota(R.drawable.nota_b_fac3, "B")
            )
            "Clave de Sol y Clave de Fa (C2-C6)" -> listOf(
                Nota(R.drawable.nota_g, "G"),
                Nota(R.drawable.nota_a, "A"),
                Nota(R.drawable.nota_b, "B"),
                Nota(R.drawable.nota_c, "C"),
                Nota(R.drawable.nota_d, "D"),
                Nota(R.drawable.nota_e, "E"),
                Nota(R.drawable.nota_f, "F"),
                Nota(R.drawable.nota_c_c5, "C"),
                Nota(R.drawable.nota_d_c5, "D"),
                Nota(R.drawable.nota_e_c5, "E"),
                Nota(R.drawable.nota_f_c5, "F"),
                Nota(R.drawable.nota_g_c5, "G"),
                Nota(R.drawable.nota_a_c5, "A"),
                Nota(R.drawable.nota_b_c5, "B"),
                Nota(R.drawable.nota_c_fac2, "C"),
                Nota(R.drawable.nota_d_fac2, "D"),
                Nota(R.drawable.nota_e_fac2, "E"),
                Nota(R.drawable.nota_f_fac2, "F"),
                Nota(R.drawable.nota_g_fac2, "G"),
                Nota(R.drawable.nota_a_fac2, "A"),
                Nota(R.drawable.nota_b_fac2, "B"),
                Nota(R.drawable.nota_c_fac3, "C"),
                Nota(R.drawable.nota_d_fac3, "D"),
                Nota(R.drawable.nota_e_fac3, "E"),
                Nota(R.drawable.nota_f_fac3, "F"),
                Nota(R.drawable.nota_g_fac3, "G"),
                Nota(R.drawable.nota_a_fac3, "A"),
                Nota(R.drawable.nota_b_fac3, "B")
            )
            "Clave de Contralto (C3-C5)" -> listOf(
                Nota(R.drawable.nota_c_do, "C"),
                Nota(R.drawable.nota_c_do, "D"),
                Nota(R.drawable.nota_e_do, "E"),
                Nota(R.drawable.nota_f_do, "F"),
                Nota(R.drawable.nota_g_do, "G"),
                Nota(R.drawable.nota_a_do, "A"),
                Nota(R.drawable.nota_b_do, "B")
            )
            else -> emptyList()
        }

        val cantidadEjercicios = if (modalidad == "Modo Cronómetro") 1000 else 20

        val ejerciciosExtendidos = (1..(cantidadEjercicios / datosPorClave.size + 1))
            .flatMap { datosPorClave }
            .shuffled()

        _imagenes.value = ejerciciosExtendidos.take(cantidadEjercicios)
        _indiceActual.value = 0
        _mensajeRespuesta.value = null
        _respuestasCorrectas.value = 0
        _respuestasIncorrectas.value = 0
    }

    // Verificar si la respuesta del usuario es correcta
    fun verificarRespuesta(respuesta: String) {
        val listaActual = _imagenes.value
        if (_indiceActual.value < listaActual.size) {
            val notaActual = listaActual[_indiceActual.value]
            if (respuesta == notaActual.nombreNota) {
                _respuestasCorrectas.value += 1
                _mensajeRespuesta.value = "Correcto"
                reproducirSonido(R.raw.correctoson) // Sonido para respuesta correcta
            } else {
                _respuestasIncorrectas.value += 1
                _mensajeRespuesta.value = "Incorrecto"
                vibrarTelefono() // Vibración para respuesta incorrecta
            }
            avanzarEjercicio()
        }
    }

    private fun avanzarEjercicio() {
        viewModelScope.launch {
            _indiceActual.value += 1

        }
    }

    private fun reproducirSonido(resId: Int) {
        mediaPlayer?.release() // Libera cualquier reproducción previa
        mediaPlayer = MediaPlayer.create(context, resId).apply {
            start()
        }
    }

    private fun vibrarTelefono() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            // Para API 31 o superior
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorManager.defaultVibrator
            vibrator.vibrate(android.os.VibrationEffect.createOneShot(200, android.os.VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            // Para versiones anteriores
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(200) // Vibrar por 200ms
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release() // Libera el MediaPlayer cuando el ViewModel se destruye
    }



}

// Clase para representar una nota musical con su imagen y nombre
data class Nota(val imagenResId: Int, val nombreNota: String)