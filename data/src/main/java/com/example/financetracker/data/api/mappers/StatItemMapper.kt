package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.StatItemDto
import com.example.financetracker.domain.models.StatItem

fun StatItemDto.toDomain(): StatItem = StatItem(
    categoryId = categoryId,
    categoryName = categoryName,
    emoji = emoji,
    amount = amount
)

fun StatItem.toData(): StatItemDto = StatItemDto(
    categoryId = categoryId,
    categoryName = categoryName,
    emoji = emoji,
    amount = amount
)