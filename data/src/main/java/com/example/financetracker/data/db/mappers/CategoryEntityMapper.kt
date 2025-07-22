package com.example.financetracker.data.db.mappers

import com.example.financetracker.data.db.entities.CategoryEntity
import com.example.financetracker.domain.models.Category

fun CategoryEntity.toDomain(): Category = Category(
    id = id,
    name = name,
    emoji = emoji,
    isIncome = isIncome
)

fun Category.toEntity(): CategoryEntity = CategoryEntity(
    id = id,
    name = name,
    emoji = emoji,
    isIncome = isIncome
)