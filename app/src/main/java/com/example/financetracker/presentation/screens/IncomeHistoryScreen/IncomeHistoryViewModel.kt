package com.example.financetracker.presentation.screens.IncomeHistoryScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.Result
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

    fun setStartDate(date: LocalDate) {
        _incomeHistoryState.value = _incomeHistoryState.value.copy(startDate = date)
        tryFetchIfBothDatesSelected()
    }

    fun setEndDate(date: LocalDate) {
        _incomeHistoryState.value = _incomeHistoryState.value.copy(endDate = date)
        tryFetchIfBothDatesSelected()
    }

    private fun tryFetchIfBothDatesSelected() {
        val start = _incomeHistoryState.value.startDate
        val end = _incomeHistoryState.value.endDate

        if (start != null && end != null) {
            if (start.isAfter(end)) {
                _incomeHistoryState.value = _incomeHistoryState.value.copy(
                    error = Result.Error.CalendarError
                )
            }

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