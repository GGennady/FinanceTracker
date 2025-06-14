package com.example.financetracker.models

data class TransactionModel(
    val id: Int,
    val account: AccountBriefModel,
    val categoryId: CategoryModel,
    val amount: String,
    val transactionDate: String,
    val comment: String? = null,
    val createdAt: String,
    val updatedAt: String,
)
