package com.example.financetracker.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_brief_table")
data class AccountBriefEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val balance: String,
    val currency: String,
)
