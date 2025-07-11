package com.example.financetracker.presentation.screens.expenses

import com.example.financetracker.domain.Result
import com.example.financetracker.domain.models.TransactionResponse

/**
 * UI state data class for the ExpensesScreen.
 *
 * Holds the state of loading, list of transactions, and possible error.
 *
 * @property isLoading Indicates whether data is currently being loaded.
 * @property transactions The list of fetched transactions.
 * @property error The occurred error during data loading (possibly).
 */
data class ExpensesUIState(
    val isLoading: Boolean = false,
    val transactions: List<TransactionResponse> = emptyList(),
    val error: Result.Error? = null,
)
