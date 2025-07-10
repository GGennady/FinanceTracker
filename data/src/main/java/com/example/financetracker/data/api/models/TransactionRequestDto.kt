package com.example.financetracker.data.api.models

import com.google.gson.annotations.SerializedName

data class TransactionRequestDto(
    @SerializedName("accountId")
    val accountId: Int,

    @SerializedName("categoryId")
    val categoryId: Int,

    @SerializedName("amount")
    val amount: String,

    @SerializedName("comment")
    val comment: String? = null,
)
