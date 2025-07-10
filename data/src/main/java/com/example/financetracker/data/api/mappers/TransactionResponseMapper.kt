package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.TransactionResponseDto
import com.example.financetracker.domain.models.TransactionResponse

fun TransactionResponseDto.toDomain(): TransactionResponse = TransactionResponse(
    id = id,
    account = account.toDomain(),
    category = category.toDomain(),
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun TransactionResponse.toData(): TransactionResponseDto = TransactionResponseDto(
    id = id,
    account = account.toData(),
    category = category.toData(),
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt
)