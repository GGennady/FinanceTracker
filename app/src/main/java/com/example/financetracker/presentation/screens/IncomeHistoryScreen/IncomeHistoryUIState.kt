package com.example.financetracker.presentation.screens.IncomeHistoryScreen

import com.example.financetracker.Result
import com.example.financetracker.data.api.model.TransactionModel
import java.time.LocalDate

data class IncomeHistoryUIState(
    val isLoading: Boolean = false,
    val transactions: List<TransactionModel> = emptyList(),
    val error: Result.Error? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
)
