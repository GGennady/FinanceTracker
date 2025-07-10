package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.AccountUpdateRequestDto
import com.example.financetracker.domain.models.AccountUpdateRequest

fun AccountUpdateRequestDto.toDomain(): AccountUpdateRequest = AccountUpdateRequest(
    name = name,
    balance = balance,
    currency = currency
)
fun AccountUpdateRequest.toData(): AccountUpdateRequestDto = AccountUpdateRequestDto(
    name = name,
    balance = balance,
    currency = currency
)
