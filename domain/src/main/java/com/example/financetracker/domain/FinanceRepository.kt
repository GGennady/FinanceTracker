package com.example.financetracker.domain

import com.example.financetracker.domain.models.Account
import com.example.financetracker.domain.models.AccountResponse
import com.example.financetracker.domain.models.Category
import com.example.financetracker.domain.models.Transaction
import com.example.financetracker.domain.models.TransactionResponse

/**
 * Domain interface for accessing finance-related data.
 *
 * Abstracts data sources and defines operations for transactions, accounts, and categories.
 */
interface FinanceRepository {
    suspend fun getAllTransactions(startDate: String? = null, endDate: String? = null): Result<List<TransactionResponse>>

    suspend fun getAccountById(): Result<AccountResponse>

    suspend fun getAllCategories(): Result<List<Category>>

    suspend fun putAccountById(name: String, balance: String, currency: String): Result<Account>

    suspend fun postTransaction(accountId: Int, categoryId: Int, amount: String, comment: String? = null): Result<Transaction>

    suspend fun putTransaction(accountId: Int, categoryId: Int, amount: String, comment: String? = null): Result<TransactionResponse>
}