package com.example.financetracker.presentation.screens.income_history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.utils.Result
import com.example.financetracker.domain.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * ViewModel for managing the IncomeHistoryScreen logic.
 *
 * Fetches transactions from the repository and shows UI state.
 *
 * @property repository The repository providing access to data.
 */
@HiltViewModel
class IncomeHistoryViewModel @Inject constructor(private val repository: FinanceRepository): ViewModel() {

    private val _incomeHistoryState = mutableStateOf(IncomeHistoryUIState(isLoading = true))
    val incomeHistoryState: State<IncomeHistoryUIState> = _incomeHistoryState

    fun getAllIncomeHistory(startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            _incomeHistoryState.value = _incomeHistoryState.value.copy(isLoading = true)

            val result = repository.getAllTransactions(
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