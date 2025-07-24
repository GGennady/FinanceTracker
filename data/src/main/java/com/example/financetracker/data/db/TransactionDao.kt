package com.example.financetracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.financetracker.data.db.entities.AccountBriefEntity
import com.example.financetracker.data.db.entities.CategoryEntity
import com.example.financetracker.data.db.entities.TransactionEntity
import com.example.financetracker.data.db.entities.relations.TransactionWithAccountAndCategory

@Dao
interface TransactionDao {

    @Transaction
    @Query(
        """
        SELECT * FROM transaction_table
        WHERE (:startDate IS NULL OR transactionDate >= :startDate)
            AND (:endDate IS NULL OR transactionDate <= :endDate)
        ORDER BY transactionDate DESC
        """
    )
    suspend fun getAllWithRelations(startDate: String?, endDate: String?): List<TransactionWithAccountAndCategory>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransactions(transactions: List<TransactionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccounts(accounts: List<AccountBriefEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM transaction_table")
    suspend fun clearTransactions()
}
