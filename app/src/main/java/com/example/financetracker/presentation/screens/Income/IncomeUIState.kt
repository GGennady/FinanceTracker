package com.example.financetracker.presentation.screens.Income

import com.example.financetracker.utils.Result
import com.example.financetracker.data.api.model.TransactionModel

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
    val transactions: List<TransactionModel> = emptyList(),
    val error: Result.Error? = null,
)
