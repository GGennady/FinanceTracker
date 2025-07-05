package com.example.financetracker.presentation.screens.income_history

import com.example.financetracker.utils.Result
import com.example.financetracker.data.api.model.TransactionModel
import java.time.LocalDate

/**
 * UI state data class for the IncomeHistoryScreen.
 *
 * Represents the loading state, list of transactions, selected date range, and possible error.
 *
 * @property isLoading Indicates whether income data is currently being loaded.
 * @property transactions The list of income transactions.
 * @property error The occurred error during data fetching (possibly).
 * @property startDate The start date of the selected period.
 * @property endDate The end date of the selected period.
 */
data class IncomeHistoryUIState(
    val isLoading: Boolean = false,
    val transactions: List<TransactionModel> = emptyList(),
    val error: Result.Error? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
)
