package com.example.financetracker.presentation.screens.IncomeHistoryScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.data.Result
import com.example.financetracker.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class IncomeHistoryViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    private val _incomeHistoryState = mutableStateOf(IncomeHistoryUIState(isLoading = true))
    val incomeHistoryState: State<IncomeHistoryUIState> = _incomeHistoryState

    fun getAllIncomeHistory(startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            _incomeHistoryState.value = _incomeHistoryState.value.copy(isLoading = true)

            val result = repository.getAllTransactions(
                accountId = 21,
                startDate = startDate,
                endDate = endDate
            )

            _incomeHistoryState.value = when (result) {
                is Result.Success -> _incomeHistoryState.value.copy(
                    transactions = result.data,
                    isLoading = false,
                    error = null
                )
                is Result.Error -> _incomeHistoryState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    private val _startDate = mutableStateOf<LocalDate?>(null)
    val startDate: State<LocalDate?> = _startDate

    private val _endDate = mutableStateOf<LocalDate?>(null)
    val endDate: State<LocalDate?> = _endDate

    fun setStartDate(date: LocalDate) {
        _startDate.value = date
        tryFetchIfBothDatesSelected()
    }

    fun setEndDate(date: LocalDate) {
        _endDate.value = date
        tryFetchIfBothDatesSelected()
    }

    private fun tryFetchIfBothDatesSelected() {
        val start = _startDate.value
        val end = _endDate.value

        if (start != null && end != null) {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            getAllIncomeHistory(
                startDate = start.format(formatter),
                endDate = end.format(formatter)
            )
        }
    }

    fun clearError() {
        _incomeHistoryState.value = _incomeHistoryState.value.copy(error = null)
    }
}