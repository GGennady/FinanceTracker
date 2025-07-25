package com.example.financetracker.presentation.screens.analysis

import com.example.financetracker.domain.Result
import com.example.financetracker.domain.models.TransactionResponse
import java.time.LocalDate

data class AnalysisUIState(
    val isLoading: Boolean = false,
    val transactions: List<TransactionResponse> = emptyList(),
    val error: Result.Error? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
)