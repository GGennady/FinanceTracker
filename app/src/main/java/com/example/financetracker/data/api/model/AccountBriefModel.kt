package com.example.financetracker.data.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class AccountBriefModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("balance")
    val balance: String,

    @SerializedName("currency")
    val currency: String,
)