package com.example.financetracker.data.api.model

import com.google.gson.annotations.SerializedName

data class AccountResponseModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("balance")
    val balance: String,

    @SerializedName("currency")
    val currency: String,

    @SerializedName("incomeStats")
    val incomeStats: List<StatItemModel>,

    @SerializedName("expenseStats")
    val expenseStats: List<StatItemModel>,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,
)
