package com.example.financetracker.domain.models

data class Transaction(
    val id: Int,

    val accountId: Int,

    val categoryId: Int,

    val amount: String,

    val transactionDate: String,

    val comment: String? = null,

    val createdAt: String,

    val updatedAt: String,
)