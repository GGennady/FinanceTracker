package com.example.financetracker.data.repository

import com.example.financetracker.NetworkMonitor
import com.example.financetracker.data.BaseApiResponse
import com.example.financetracker.Result
import com.example.financetracker.data.api.ApiService
import com.example.financetracker.data.api.model.AccountResponseModel
import com.example.financetracker.data.api.model.CategoryModel
import com.example.financetracker.data.api.model.TransactionModel
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: ApiService, networkMonitor: NetworkMonitor): BaseApiResponse(networkMonitor) {

    suspend fun getAllTransactions(accountId: Int, startDate: String? = null, endDate: String? = null): Result<List<TransactionModel>> {
        return safeApiCall( suspend { api.getAllTransactions(accountId, startDate, endDate) } )
    }

    suspend fun getAccountById(id: Int): Result<AccountResponseModel> {
        return safeApiCall( suspend { api.getAccountById(id) } )
    }

    suspend fun getAllCategories(): Result<List<CategoryModel>> {
        return safeApiCall( suspend { api.getAllCategories() } )
    }
}