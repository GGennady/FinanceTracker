package com.example.financetracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financetracker.data.db.entities.AccountBriefEntity
import com.example.financetracker.data.db.entities.CategoryEntity
import com.example.financetracker.data.db.entities.TransactionEntity

@Database(
    entities = [
        AccountBriefEntity::class,
        CategoryEntity::class,
        TransactionEntity::class,
    ],
    version = 3,
    exportSchema = true
)
abstract class FinanceDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    abstract fun categoryDao(): CategoryDao
}