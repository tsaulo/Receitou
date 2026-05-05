package com.example.trabalho.model

import java.io.Serializable

data class Receita(
    val id: Int,
    val nome: String,
    val dificuldade: String,
    val ingredientes: String,
    val modoPreparo: String,
    val aprendida: Boolean
) : Serializable