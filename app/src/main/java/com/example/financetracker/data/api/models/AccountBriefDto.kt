package com.example.financetracker.data.api.models

import com.google.gson.annotations.SerializedName

data class AccountBriefDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("balance")
    val balance: String,

    @SerializedName("currency")
    val currency: String,
)