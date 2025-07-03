package com.example.financetracker.data.api

import com.example.financetracker.data.api.model.AccountModel
import com.example.financetracker.data.api.model.AccountResponseModel
import com.example.financetracker.data.api.model.AccountUpdateRequestModel
import com.example.financetracker.data.api.model.CategoryModel
import com.example.financetracker.data.api.model.TransactionModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Defines the API service for connection with backend endpoints.
 *
 * Provides methods to get transactions, account and categories.
 */
interface ApiService {

    @GET("transactions/account/{accountId}/period")
    suspend fun getAllTransactions(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<TransactionModel>

    @GET("accounts/{id}")
    suspend fun getAccountById(
        @Path("id") id: Int,
    ): AccountResponseModel

    @GET("categories")
    suspend fun getAllCategories(): List<CategoryModel>

    @PUT("accounts/{id}")
    suspend fun putAccountById(
        @Path("id") id: Int,
        @Body body: AccountUpdateRequestModel
    ): AccountModel
}