package com.example.financetracker.presentation.screens.my_account

import com.example.financetracker.domain.models.Account
import com.example.financetracker.domain.models.AccountResponse
import com.example.financetracker.utils.Result

/**
 * UI state data class for the My Account screen.
 *
 * Holds the current account data, loading state, and any occurred error.
 *
 * @property isLoading Indicates whether account data is currently being loaded.
 * @property account The user's account information.
 * @property error The occurred error during data fetching (possibly).
 */
data class MyAccountUIState (
    val isLoading: Boolean = false,
    val account: AccountResponse? = null,
    val accountAfterPut: Account? = null,
    val error: Result.Error? = null,
)