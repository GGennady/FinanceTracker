package com.example.financetracker.data.api.models

import com.google.gson.annotations.SerializedName

data class TransactionDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("accountId")
    val accountId: Int,

    @SerializedName("categoryId")
    val categoryId: Int,

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
