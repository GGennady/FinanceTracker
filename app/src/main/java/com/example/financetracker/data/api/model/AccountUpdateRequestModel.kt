package com.example.financetracker.data.api.model

import com.google.gson.annotations.SerializedName

data class AccountUpdateRequestModel(
    @SerializedName("name")
    val name: String,

    @SerializedName("balance")
    val balance: String,

    @SerializedName("currency")
    val currency: String
)