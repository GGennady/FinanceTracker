package com.example.financetracker.data.api.model

import com.google.gson.annotations.SerializedName

data class StatItemModel(
    @SerializedName("categoryId")
    val categoryId: Int,

    @SerializedName("categoryName")
    val categoryName: String,

    @SerializedName("emoji")
    val emoji: String,

    @SerializedName("amount")
    val amount: String,
)
