package com.example.financetracker.domain.models

data class Category(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean,
)
