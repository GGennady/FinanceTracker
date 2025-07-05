package com.example.financetracker.presentation.screens.expenses

import com.example.financetracker.utils.Result
import com.example.financetracker.data.api.model.TransactionModel

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
    val transactions: List<TransactionModel> = emptyList(),
    val error: Result.Error? = null,
)
