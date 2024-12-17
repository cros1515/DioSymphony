package com.example.diosymphony.data.repository

import com.example.diosymphony.R
import com.example.diosymphony.data.model.DictionaryTerm

class DictionaryRepository {

    private val terms = listOf(
        // Categoría: Notas
        DictionaryTerm("Do", R.drawable.nota_c, "Primera nota musical en el sistema moderno."),
        DictionaryTerm("Re", R.drawable.nota_d, "Segunda nota musical."),
        DictionaryTerm("Mi", R.drawable.nota_e, "Tercera nota musical."),
        DictionaryTerm("Fa", R.drawable.nota_f, "Cuarta nota musical."),
        DictionaryTerm("Sol", R.drawable.nota_g, "Quinta nota musical."),
        DictionaryTerm("La", R.drawable.nota_a, "Sexta nota musical."),
        DictionaryTerm("Si", R.drawable.nota_b, "Séptima y última nota musical."),

        // Categoría: Acordes
        DictionaryTerm("Acorde Mayor", R.drawable.notas1, "Un acorde formado por una tercera mayor y una quinta justa."),
        DictionaryTerm("Acorde Menor", R.drawable.notas1, "Un acorde formado por una tercera menor y una quinta justa."),
        DictionaryTerm("Acorde Disminuido", R.drawable.notas1, "Un acorde con una tercera menor y una quinta disminuida."),
        DictionaryTerm("Acorde de Séptima", null, "Un acorde que incluye una séptima menor sobre la fundamental."),

        // Categoría: Dinámicas
        DictionaryTerm("Forte (f)", null, "Indica que una sección debe tocarse con fuerza."),
        DictionaryTerm("Piano (p)", null, "Indica que una sección debe tocarse suavemente."),
        DictionaryTerm("Mezzo Forte (mf)", null, "Indica que una sección debe tocarse moderadamente fuerte."),
        DictionaryTerm("Crescendo (<)", null, "Indica un aumento gradual en el volumen."),
        DictionaryTerm("Decrescendo (>)", null, "Indica una disminución gradual en el volumen."),

        // Categoría: Términos generales
        DictionaryTerm("Arpegio", null, "Técnica en la que las notas de un acorde se tocan una a la vez en lugar de juntas."),
        DictionaryTerm("Legato", null, "Indica que las notas deben tocarse de forma suave y conectada."),
        DictionaryTerm("Staccato", null, "Indica que las notas deben tocarse de forma corta y separada."),
        DictionaryTerm("Pentagrama", R.drawable.clavsol, "El conjunto de cinco líneas y cuatro espacios donde se escriben las notas."),
        DictionaryTerm("Clave de Sol", R.drawable.clavsol, "Símbolo que indica las notas para tonos agudos."),
        DictionaryTerm("Clave de Fa", R.drawable.famanual, "Símbolo que indica las notas para tonos graves.")
    )

    fun getTerms(category: String): List<DictionaryTerm> {
        return when (category) {
            "Todos" -> terms
            "Notas" -> terms.filter { it.name in listOf("Do", "Re", "Mi", "Fa", "Sol", "La", "Si") }
            "Acordes" -> terms.filter { it.name.startsWith("Acorde") }
            "Dinámicas" -> terms.filter { it.name.contains("f") || it.name.contains("<") || it.name.contains(">") }
            else -> terms
        }
    }
}

