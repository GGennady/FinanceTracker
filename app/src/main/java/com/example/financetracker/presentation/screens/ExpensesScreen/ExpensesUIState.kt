package com.example.financetracker.presentation.screens.ExpensesScreen

import com.example.financetracker.Result
import com.example.financetracker.data.api.model.TransactionModel

data class ExpensesUIState(
    val isLoading: Boolean = false,
    val transactions: List<TransactionModel> = emptyList(),
    val error: Result.Error? = null,
)
