package com.example.financetracker.data.repository

import com.example.financetracker.data.AccountIdStorage
import com.example.financetracker.data.BaseApiResponse
import com.example.financetracker.data.NetworkMonitor
import com.example.financetracker.data.api.ApiService
import com.example.financetracker.data.api.mappers.toDomain
import com.example.financetracker.data.api.models.AccountUpdateRequestDto
import com.example.financetracker.domain.FinanceRepository
import com.example.financetracker.domain.models.Account
import com.example.financetracker.domain.models.AccountResponse
import com.example.financetracker.domain.models.Category
import com.example.financetracker.domain.models.Transaction
import com.example.financetracker.utils.Result
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

    override suspend fun getAllTransactions(startDate: String?, endDate: String?): Result<List<Transaction>> {
        val accountId = accountIdStorage.getAccountId().first()
        return safeApiCall( { api.getAllTransactions(accountId, startDate, endDate).map { it.toDomain() } } )
    }

    override suspend fun getAccountById(): Result<AccountResponse> {
        val id = accountIdStorage.getAccountId().first()
        return safeApiCall( { api.getAccountById(id).toDomain() } )
    }

    override suspend fun getAllCategories(): Result<List<Category>> {
        return safeApiCall( { api.getAllCategories().map { it.toDomain() } } )
    }

    override suspend fun putAccountById(name: String, balance: String, currency: String): Result<Account> {
        val id = accountIdStorage.getAccountId().first()
        val requestBody = AccountUpdateRequestDto(name, balance, currency)
        return safeApiCall( { api.putAccountById(id, requestBody).toDomain() } )
    }
}