package com.example.graphs

import java.time.LocalDate

data class ExpensesGraphElement(
    val date: LocalDate,
    val amount: Float,
    val isPositive: Boolean // доход или нет
)
