package com.example.financetracker.presentation.screens.MyAccountScreen

import com.example.financetracker.Result
import com.example.financetracker.data.api.model.AccountResponseModel

data class MyAccountUIState (
    val isLoading: Boolean = false,
    val account: AccountResponseModel? = null,
    val error: Result.Error? = null,
)