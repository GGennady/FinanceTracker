package com.example.financetracker.data.repository

import com.example.financetracker.NetworkMonitor
import com.example.financetracker.data.BaseApiResponse
import com.example.financetracker.data.Result
import com.example.financetracker.data.api.ApiService
import com.example.financetracker.data.api.model.AccountResponseModel
import com.example.financetracker.data.api.model.CategoryModel
import com.example.financetracker.data.api.model.TransactionModel
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: ApiService, private val networkMonitor: NetworkMonitor): BaseApiResponse() {

    suspend fun getAllTransactions(accountId: Int, startDate: String? = null, endDate: String? = null): Result<List<TransactionModel>> {
        return safeApiCall( suspend { api.getAllTransactions(accountId, startDate, endDate) }, networkMonitor )
    }

    suspend fun getAccountById(id: Int): Result<AccountResponseModel> {
        return safeApiCall( suspend { api.getAccountById(id) }, networkMonitor )
    }

    suspend fun getAllCategories(): Result<List<CategoryModel>> {
        return safeApiCall( suspend { api.getAllCategories() }, networkMonitor )
    }
}