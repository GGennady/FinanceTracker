package com.example.financetracker.data.db.mappers

import com.example.financetracker.data.db.entities.TransactionEntity
import com.example.financetracker.data.db.entities.relations.TransactionWithAccountAndCategory
import com.example.financetracker.domain.models.Transaction
import com.example.financetracker.domain.models.TransactionResponse

fun TransactionWithAccountAndCategory.toDomain(): TransactionResponse = TransactionResponse(
    id = transaction.id,
    account = account.toDomain(),
    category = category.toDomain(),
    amount = transaction.amount,
    transactionDate = transaction.transactionDate,
    comment = transaction.comment,
    createdAt = transaction.createdAt,
    updatedAt = transaction.updatedAt,
)

fun TransactionResponse.toEntity(): TransactionEntity = TransactionEntity(
    id = id,
    accountId = account.id,
    categoryId = category.id,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isSynced = true
)