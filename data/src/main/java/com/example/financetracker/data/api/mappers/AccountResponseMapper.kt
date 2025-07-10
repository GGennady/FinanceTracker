package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.AccountResponseDto
import com.example.financetracker.domain.models.AccountResponse

fun AccountResponseDto.toDomain(): AccountResponse = AccountResponse(
    id = id,
    name = name,
    balance = balance,
    currency = currency,
    incomeStats = incomeStats.map { it.toDomain() },
    expenseStats = expenseStats.map { it.toDomain() },
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun AccountResponse.toData(): AccountResponseDto = AccountResponseDto(
    id = id,
    name = name,
    balance = balance,
    currency = currency,
    incomeStats = incomeStats.map { it.toData() },
    expenseStats = expenseStats.map { it.toData() },
    createdAt = createdAt,
    updatedAt = updatedAt
)