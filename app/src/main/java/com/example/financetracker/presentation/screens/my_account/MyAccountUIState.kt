package com.example.financetracker.presentation.screens.my_account

import com.example.financetracker.data.api.model.AccountModel
import com.example.financetracker.utils.Result
import com.example.financetracker.data.api.model.AccountResponseModel

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
    val account: AccountResponseModel? = null,
    val accountAfterPut: AccountModel? = null,
    val error: Result.Error? = null,
)