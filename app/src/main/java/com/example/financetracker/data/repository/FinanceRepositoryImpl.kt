package com.example.financetracker.data.repository

import com.example.financetracker.data.AccountIdStorage
import com.example.financetracker.data.NetworkMonitor
import com.example.financetracker.data.BaseApiResponse
import com.example.financetracker.utils.Result
import com.example.financetracker.data.api.ApiService
import com.example.financetracker.data.api.model.AccountModel
import com.example.financetracker.data.api.model.AccountResponseModel
import com.example.financetracker.data.api.model.AccountUpdateRequestModel
import com.example.financetracker.data.api.model.CategoryModel
import com.example.financetracker.data.api.model.TransactionModel
import com.example.financetracker.domain.FinanceRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Implementation of the FinanceRepository interface.
 *
 * Handles data retrieval by exec safeApiCall funs.
 *
 * @property api The API service used for network requests.
 * @constructor Injects ApiService and NetworkMonitor dependencies.
 */

class FinanceRepositoryImpl @Inject constructor(
    private val api: ApiService,
    networkMonitor: NetworkMonitor,
    private val accountIdStorage: AccountIdStorage,
): FinanceRepository, BaseApiResponse(networkMonitor) {

    override suspend fun getAllTransactions(startDate: String?, endDate: String?): Result<List<TransactionModel>> {
        val accountId = accountIdStorage.getAccountId().first()
        return safeApiCall( { api.getAllTransactions(accountId, startDate, endDate) } )
    }

    override suspend fun getAccountById(): Result<AccountResponseModel> {
        val id = accountIdStorage.getAccountId().first()
        return safeApiCall( { api.getAccountById(id) } )
    }

    override suspend fun getAllCategories(): Result<List<CategoryModel>> {
        return safeApiCall( { api.getAllCategories() } )
    }

    override suspend fun putAccountById(name: String, balance: String, currency: String): Result<AccountModel> {
        val id = accountIdStorage.getAccountId().first()
        val requestBody = AccountUpdateRequestModel(name, balance, currency)
        return safeApiCall( { api.putAccountById(id, requestBody) } )
    }
}