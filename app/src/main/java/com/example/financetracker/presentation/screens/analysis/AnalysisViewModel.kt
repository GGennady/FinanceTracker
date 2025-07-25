package com.example.financetracker.presentation.screens.analysis

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.domain.FinanceRepository
import com.example.financetracker.domain.Result
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AnalysisViewModel @Inject constructor(private val repository: FinanceRepository): ViewModel() {

    private val _analysisState = mutableStateOf(AnalysisUIState(isLoading = true))
    val analysisState: State<AnalysisUIState> = _analysisState


    fun getAllTransactionsHistory(startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            _analysisState.value = _analysisState.value.copy(isLoading = true)

            val result = repository.getAllTransactions(
                startDate = startDate,
                endDate = endDate
            )

            _analysisState.value = when (result) {
                is Result.Success -> _analysisState.value.copy(
                    transactions = result.data,
                    isLoading = false,
                    error = null
                )
                is Result.Error -> _analysisState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun setStartDate(date: LocalDate) {
        _analysisState.value = _analysisState.value.copy(startDate = date)
        tryFetchIfBothDatesSelected()
    }

    fun setEndDate(date: LocalDate) {
        _analysisState.value = _analysisState.value.copy(endDate = date)
        tryFetchIfBothDatesSelected()
    }

    private fun tryFetchIfBothDatesSelected() {
        val start = _analysisState.value.startDate
        val end = _analysisState.value.endDate

        if (start != null && end != null) {
            if (start.isAfter(end)) {
                _analysisState.value = _analysisState.value.copy(
                    error = Result.Error.CalendarError
                )
            }

            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            getAllTransactionsHistory(
                startDate = start.format(formatter),
                endDate = end.format(formatter)
            )
        }
    }

    fun clearError() {
        _analysisState.value = _analysisState.value.copy(error = null)
    }
}