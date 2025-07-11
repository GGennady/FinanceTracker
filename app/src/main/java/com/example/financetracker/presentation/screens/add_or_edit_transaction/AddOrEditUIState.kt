package com.example.financetracker.presentation.screens.add_or_edit_transaction

import com.example.financetracker.domain.Result
import com.example.financetracker.domain.models.AccountResponse
import com.example.financetracker.domain.models.Category
import com.example.financetracker.domain.models.Transaction
import com.example.financetracker.domain.models.TransactionResponse

data class AddOrEditUIState(
    val isLoading: Boolean = false,
    val transaction: TransactionResponse? = null,
    val transactionPost: Transaction? = null,
    val categories: List<Category> = emptyList(),
    val account: AccountResponse? = null,
    val error: Result.Error? = null,
    val transactionSaved: Boolean = false
)
