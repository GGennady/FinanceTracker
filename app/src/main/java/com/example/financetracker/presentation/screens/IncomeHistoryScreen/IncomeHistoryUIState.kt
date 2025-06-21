package com.example.financetracker.presentation.screens.IncomeHistoryScreen

import com.example.financetracker.data.Result
import com.example.financetracker.data.api.model.TransactionModel

data class IncomeHistoryUIState(
    val isLoading: Boolean = false,
    val transactions: List<TransactionModel> = emptyList(),
    val error: Result.Error? = null,
)
