package com.example.financetracker.data.api

import com.example.financetracker.data.api.models.AccountDto
import com.example.financetracker.data.api.models.AccountResponseDto
import com.example.financetracker.data.api.models.AccountUpdateRequestDto
import com.example.financetracker.data.api.models.CategoryDto
import com.example.financetracker.data.api.models.TransactionDto
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
    ): List<TransactionDto>

    @GET("accounts/{id}")
    suspend fun getAccountById(
        @Path("id") id: Int,
    ): AccountResponseDto

    @GET("categories")
    suspend fun getAllCategories(): List<CategoryDto>

    @PUT("accounts/{id}")
    suspend fun putAccountById(
        @Path("id") id: Int,
        @Body body: AccountUpdateRequestDto
    ): AccountDto
}