package com.example.financetracker.domain.models

data class AccountBrief(
    val id: Int,

    val name: String,

    val balance: String,

    val currency: String,
)
