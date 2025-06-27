package com.example.financetracker.data.repository

import com.example.financetracker.utils.NetworkMonitor
import com.example.financetracker.data.BaseApiResponse
import com.example.financetracker.utils.Result
import com.example.financetracker.data.api.ApiService
import com.example.financetracker.data.api.model.AccountResponseModel
import com.example.financetracker.data.api.model.CategoryModel
import com.example.financetracker.data.api.model.TransactionModel
import com.example.financetracker.domain.FinanceRepository
import javax.inject.Inject

/**
 * Implementation of the FinanceRepository interface.
 *
 * Handles data retrieval by exec safeApiCall funs.
 *
 * @property api The API service used for network requests.
 * @constructor Injects ApiService and NetworkMonitor dependencies.
 */
class FinanceRepositoryImpl @Inject constructor(private val api: ApiService, networkMonitor: NetworkMonitor): FinanceRepository, BaseApiResponse(networkMonitor) {

    override suspend fun getAllTransactions(accountId: Int, startDate: String?, endDate: String?): Result<List<TransactionModel>> {
        return safeApiCall( { api.getAllTransactions(accountId, startDate, endDate) } )
    }

    override suspend fun getAccountById(id: Int): Result<AccountResponseModel> {
        return safeApiCall( { api.getAccountById(id) } )
    }

    override suspend fun getAllCategories(): Result<List<CategoryModel>> {
        return safeApiCall( { api.getAllCategories() } )
    }
}