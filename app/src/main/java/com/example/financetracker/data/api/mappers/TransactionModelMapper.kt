package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.TransactionDto
import com.example.financetracker.domain.models.Transaction

fun TransactionDto.toDomain(): Transaction = Transaction(
    id = id,
    account = account.toDomain(),
    category = category.toDomain(),
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Transaction.toData(): TransactionDto = TransactionDto(
    id = id,
    account = account.toData(),
    category = category.toData(),
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt
)