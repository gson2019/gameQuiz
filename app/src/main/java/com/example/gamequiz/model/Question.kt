package com.example.quiz.model


import com.google.gson.annotations.SerializedName

data class Question(
    val answer: String,
    val options: List<String>,
    val question: String
)