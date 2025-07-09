package com.example.financetracker.presentation.screens.Income

import com.example.financetracker.domain.models.Transaction
import com.example.financetracker.utils.Result

/**
 * UI state data class for the IncomeScreen.
 *
 * Holds the state of loading, list of transactions, and possible error.
 *
 * @property isLoading Indicates whether data is currently being loaded.
 * @property transactions The list of fetched transactions.
 * @property error The occurred error during data loading (possibly).
 */
data class IncomeUIState(
    val isLoading: Boolean = false,
    val transactions: List<Transaction> = emptyList(),
    val error: Result.Error? = null,
)
