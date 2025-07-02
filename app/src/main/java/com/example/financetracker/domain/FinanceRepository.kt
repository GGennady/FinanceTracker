package com.example.financetracker.domain

import com.example.financetracker.data.api.model.AccountResponseModel
import com.example.financetracker.data.api.model.CategoryModel
import com.example.financetracker.data.api.model.TransactionModel
import com.example.financetracker.utils.Result

/**
 * Domain interface for accessing finance-related data.
 *
 * Abstracts data sources and defines operations for transactions, accounts, and categories.
 */
interface FinanceRepository {
    suspend fun getAllTransactions(startDate: String? = null, endDate: String? = null): Result<List<TransactionModel>>

    suspend fun getAccountById(): Result<AccountResponseModel>

    suspend fun getAllCategories(): Result<List<CategoryModel>>
}