package com.example.financetracker.domain.models

data class AccountUpdateRequest(
    val name: String,
    val balance: String,
    val currency: String,
)
