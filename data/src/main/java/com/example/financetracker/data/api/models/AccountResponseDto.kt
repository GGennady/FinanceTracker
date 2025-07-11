package com.example.financetracker.data.api.models

import com.google.gson.annotations.SerializedName

data class AccountResponseDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("balance")
    val balance: String,

    @SerializedName("currency")
    val currency: String,

    @SerializedName("incomeStats")
    val incomeStats: List<StatItemDto>,

    @SerializedName("expenseStats")
    val expenseStats: List<StatItemDto>,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,
)
