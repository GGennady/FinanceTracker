package com.example.financetracker.data.repository

import com.example.financetracker.data.AccountIdStorage
import com.example.financetracker.data.BaseApiResponse
import com.example.financetracker.data.NetworkMonitor
import com.example.financetracker.data.api.ApiService
import com.example.financetracker.data.api.mappers.toData
import com.example.financetracker.data.api.mappers.toDomain
import com.example.financetracker.data.db.CategoryDao
import com.example.financetracker.data.db.TransactionDao
import com.example.financetracker.data.db.mappers.toDomain
import com.example.financetracker.data.db.mappers.toEntity
import com.example.financetracker.domain.FinanceRepository
import com.example.financetracker.domain.Result
import com.example.financetracker.domain.models.Account
import com.example.financetracker.domain.models.AccountResponse
import com.example.financetracker.domain.models.AccountUpdateRequest
import com.example.financetracker.domain.models.Category
import com.example.financetracker.domain.models.Transaction
import com.example.financetracker.domain.models.TransactionRequest
import com.example.financetracker.domain.models.TransactionResponse
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
    private val transactionDao: TransactionDao,
    private val categoryDao: CategoryDao,
): FinanceRepository, BaseApiResponse(networkMonitor) {

    override suspend fun getAllTransactions(startDate: String?, endDate: String?): Result<List<TransactionResponse>> {
        if(networkMonitor.hasNetworkConnection()) {
            val accountId = accountIdStorage.getAccountId().first()
            val remoteTransactions = api.getAllTransactions(accountId, startDate, endDate).map { it.toDomain() }
            saveTransactions(remoteTransactions)
            return safeApiCall( { remoteTransactions } )
        } else {
            val localTransactions = transactionDao.getAllWithRelations(startDate, endDate)
            return if(localTransactions.isEmpty()) {
                Result.Error.OfflineError
            } else {
                Result.Success(localTransactions.map { it.toDomain() })
            }
        }
    }

    override suspend fun getTransactionById(transactionId: Int): Result<TransactionResponse> {
        return safeApiCall( { api.getTransactionById(transactionId).toDomain() } )
    }

    override suspend fun getAccountById(): Result<AccountResponse> {
        val id = accountIdStorage.getAccountId().first()
        return safeApiCall( { api.getAccountById(id).toDomain() } )
    }

    override suspend fun getAllCategories(): Result<List<Category>> {
        if(networkMonitor.hasNetworkConnection()) {
            val remoteCategories = api.getAllCategories().map { it.toDomain() }
            saveCategories(remoteCategories)
            return safeApiCall( { remoteCategories } )
        } else {
            val localCategories = categoryDao.getAll()
            return if(localCategories.isEmpty()) {
                Result.Error.OfflineError
            } else {
                Result.Success(localCategories.map { it.toDomain() })
            }
        }
    }

    override suspend fun putAccountById(name: String, balance: String, currency: String): Result<Account> {
        val id = accountIdStorage.getAccountId().first()
        val requestBody = AccountUpdateRequest(name, balance, currency).toData()
        return safeApiCall( { api.putAccountById(id, requestBody).toDomain() } )
    }

    override suspend fun postTransaction(accountId: Int, categoryId: Int, amount: String, transactionDate: String, comment: String?): Result<Transaction> {
        val requestBody = TransactionRequest(accountId, categoryId, amount, transactionDate, comment).toData()
        return safeApiCall( { api.postTransaction(requestBody).toDomain() } )
    }

    override suspend fun putTransaction(id: Int, accountId: Int, categoryId: Int, amount: String, transactionDate: String, comment: String?): Result<TransactionResponse> {
        val requestBody = TransactionRequest(accountId, categoryId, amount, transactionDate, comment).toData()
        return safeApiCall( { api.putTransaction(id, requestBody).toDomain() } )
    }


    private suspend fun saveTransactions(transactions: List<TransactionResponse>) {
        val entities = transactions.map { it.toEntity() }
        val accounts = transactions.map { it.account.toEntity() }.distinctBy { it.id }
        val categories = transactions.map { it.category.toEntity() }.distinctBy { it.id }

        //transactionDao.clearTransactions()
        transactionDao.insertAccounts(accounts)
        transactionDao.insertCategories(categories)
        transactionDao.insertTransactions(entities)
    }

    private suspend fun saveCategories(categories: List<Category>) {
        val categories = categories.map { it.toEntity() }
        categoryDao.insertCategories(categories)
    }
}