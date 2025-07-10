package com.example.financetracker.data.api.models

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("emoji")
    val emoji: String,

    @SerializedName("isIncome")
    val isIncome: Boolean,
)