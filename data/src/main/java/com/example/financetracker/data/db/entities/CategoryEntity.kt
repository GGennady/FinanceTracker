package com.example.financetracker.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean,
)
