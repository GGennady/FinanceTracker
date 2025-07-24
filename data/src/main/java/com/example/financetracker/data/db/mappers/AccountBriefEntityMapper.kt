package com.example.financetracker.data.db.mappers

import com.example.financetracker.data.db.entities.AccountBriefEntity
import com.example.financetracker.domain.models.AccountBrief

fun AccountBriefEntity.toDomain(): AccountBrief = AccountBrief(
    id = id,
    name = name,
    balance = balance,
    currency = currency
)

fun AccountBrief.toEntity(): AccountBriefEntity = AccountBriefEntity(
    id = id,
    name = name,
    balance = balance,
    currency = currency
)