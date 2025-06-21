package com.example.financetracker.presentation.screens.ExpensesHistoryScreen

import com.example.financetracker.data.Result
import com.example.financetracker.data.api.model.TransactionModel

data class ExpensesHistoryUIState(
    val isLoading: Boolean = false,
    val transactions: List<TransactionModel> = emptyList(),
    val error: Result.Error? = null,
)
