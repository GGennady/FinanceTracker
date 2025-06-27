package com.example.financetracker.presentation.screens.ExpensesHistoryScreen

import com.example.financetracker.Result
import com.example.financetracker.data.api.model.TransactionModel
import java.time.LocalDate

data class ExpensesHistoryUIState(
    val isLoading: Boolean = false,
    val transactions: List<TransactionModel> = emptyList(),
    val error: Result.Error? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
)
