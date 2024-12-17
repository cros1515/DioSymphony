package com.example.diosymphony.domain.usecases

import com.example.diosymphony.data.model.DictionaryTerm
import com.example.diosymphony.data.repository.DictionaryRepository

class GetDictionaryTermsUseCase(private val repository: DictionaryRepository) {
    fun execute(category: String): List<DictionaryTerm> {
        return repository.getTerms(category)
    }
}
