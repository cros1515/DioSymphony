package com.example.diosymphony.ui.dictionary

import androidx.lifecycle.ViewModel
import com.example.diosymphony.data.model.DictionaryTerm
import com.example.diosymphony.data.repository.DictionaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DictionaryViewModel : ViewModel() {

    private val repository = DictionaryRepository()

    private val _terms = MutableStateFlow<List<DictionaryTerm>>(emptyList())
    val terms: StateFlow<List<DictionaryTerm>> get() = _terms

    private val _selectedCategory = MutableStateFlow("Todos")
    val selectedCategory: StateFlow<String> get() = _selectedCategory

    init {
        loadTerms("Todos")
    }

    fun loadTerms(category: String) {
        _selectedCategory.value = category
        _terms.value = repository.getTerms(category)
    }
}


