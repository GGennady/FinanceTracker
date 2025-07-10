package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.TransactionDto
import com.example.financetracker.domain.models.Transaction

fun TransactionDto.toDomain(): Transaction = Transaction(
    id = id,
    accountId = accountId,
    categoryId = categoryId,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Transaction.toData(): TransactionDto = TransactionDto(
    id = id,
    accountId = accountId,
    categoryId = categoryId,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt
)