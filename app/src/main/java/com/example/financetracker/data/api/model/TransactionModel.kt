package com.example.financetracker.data.api.model

import com.google.gson.annotations.SerializedName

data class TransactionModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("account")
    val account: AccountBriefModel,

    @SerializedName("category")
    val category: CategoryModel,

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
