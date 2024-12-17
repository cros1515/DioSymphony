package com.example.diosymphony.domain.usecases

import com.example.diosymphony.data.model.Nota
import com.example.diosymphony.data.repository.NotaRepository

class GetNotasUseCase(private val repository: NotaRepository) {
    fun execute(clave: String): List<Nota> {
        return repository.getNotasPorClave(clave)
    }
}
