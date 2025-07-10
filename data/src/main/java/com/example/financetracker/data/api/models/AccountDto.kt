package com.example.financetracker.data.api.models

import com.google.gson.annotations.SerializedName

data class AccountDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("balance")
    val balance: String,

    @SerializedName("currency")
    val currency: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,
)
