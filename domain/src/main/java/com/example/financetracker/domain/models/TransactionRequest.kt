package com.example.financetracker.domain.models

data class TransactionRequest(
    val accountId: Int,

    val categoryId: Int,

    val amount: String,

    val comment: String? = null,
)
