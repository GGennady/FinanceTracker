package com.example.financetracker.data.api.models

import com.google.gson.annotations.SerializedName

data class StatItemDto(
    @SerializedName("categoryId")
    val categoryId: Int,

    @SerializedName("categoryName")
    val categoryName: String,

    @SerializedName("emoji")
    val emoji: String,

    @SerializedName("amount")
    val amount: String,
)
