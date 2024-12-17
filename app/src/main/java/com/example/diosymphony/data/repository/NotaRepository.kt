package com.example.diosymphony.data.repository

import com.example.diosymphony.R
import com.example.diosymphony.data.model.Nota

class NotaRepository {
    private val notas = listOf(
        Nota(R.drawable.nota_c, "Do"),
        Nota(R.drawable.nota_d, "Re"),
        // Agregar más notas
    )

    fun getNotasPorClave(clave: String): List<Nota> {
        // Filtrar según la clave
        return notas // Simplificación, adaptar según necesidad
    }
}
