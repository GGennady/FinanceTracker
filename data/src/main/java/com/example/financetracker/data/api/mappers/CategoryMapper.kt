package com.example.financetracker.data.api.mappers

import com.example.financetracker.data.api.models.CategoryDto
import com.example.financetracker.domain.models.Category

fun CategoryDto.toDomain(): Category = Category(
    id = id,
    name = name,
    emoji = emoji,
    isIncome = isIncome
)

fun Category.toData(): CategoryDto = CategoryDto(
    id = id,
    name = name,
    emoji = emoji,
    isIncome = isIncome
)