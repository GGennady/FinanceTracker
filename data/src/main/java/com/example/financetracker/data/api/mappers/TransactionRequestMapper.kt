package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.TransactionRequestDto
import com.example.financetracker.domain.models.TransactionRequest

fun TransactionRequestDto.toDomain(): TransactionRequest = TransactionRequest(
    accountId = accountId,
    categoryId = categoryId,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
)

fun TransactionRequest.toData(): TransactionRequestDto = TransactionRequestDto(
    accountId = accountId,
    categoryId = categoryId,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
)