package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.AccountDto
import com.example.financetracker.domain.models.Account

fun AccountDto.toDomain(): Account = Account(
    id = id,
    userId = userId,
    name = name,
    balance = balance,
    currency = currency,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Account.toData(): AccountDto = AccountDto(
    id = id,
    userId = userId,
    name = name,
    balance = balance,
    currency = currency,
    createdAt = createdAt,
    updatedAt = updatedAt
)