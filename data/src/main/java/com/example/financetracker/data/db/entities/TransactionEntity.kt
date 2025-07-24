package com.example.financetracker.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transaction_table",
    foreignKeys = [
        ForeignKey(
            entity = AccountBriefEntity::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("accountId"), Index("categoryId")]
)
data class TransactionEntity(
    @PrimaryKey val id: Int,
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String? = null,
    val createdAt: String,
    val updatedAt: String,
    val isSynced: Boolean = true
)
