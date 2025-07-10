package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.AccountBriefDto
import com.example.financetracker.domain.models.AccountBrief

fun AccountBriefDto.toDomain(): AccountBrief = AccountBrief(
    id = id,
    name = name,
    balance = balance,
    currency = currency
)

fun AccountBrief.toData(): AccountBriefDto = AccountBriefDto(
    id = id,
    name = name,
    balance = balance,
    currency = currency
)