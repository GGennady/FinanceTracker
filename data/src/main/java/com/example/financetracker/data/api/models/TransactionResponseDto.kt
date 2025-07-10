package com.example.financetracker.data.api.models

import com.google.gson.annotations.SerializedName

data class TransactionResponseDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("account")
    val account: AccountBriefDto,

    @SerializedName("category")
    val category: CategoryDto,

    @SerializedName("amount")
    val amount: String,

    @SerializedName("transactionDate")
    val transactionDate: String,

    @SerializedName("comment")
    val comment: String? = null,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,
)
