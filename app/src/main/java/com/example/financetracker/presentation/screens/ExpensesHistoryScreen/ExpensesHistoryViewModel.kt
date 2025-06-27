package com.example.financetracker.presentation.screens.ExpensesHistoryScreen

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
 * ViewModel for managing the ExpenseHistoryScreen logic.
 *
 * Fetches transactions from the repository and shows UI state.
 *
 * @property repository The repository providing access to data.
 */
@HiltViewModel
class ExpensesHistoryViewModel @Inject constructor(private val repository: FinanceRepository): ViewModel() {

    private val _expensesHistoryState = mutableStateOf(ExpensesHistoryUIState(isLoading = true))
    val expensesHistoryState: State<ExpensesHistoryUIState> = _expensesHistoryState

    fun getAllExpensesHistory(startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            _expensesHistoryState.value = _expensesHistoryState.value.copy(isLoading = true)

            val result = repository.getAllTransactions(
                accountId = 21,
                startDate = startDate,
                endDate = endDate
            )

            _expensesHistoryState.value = when (result) {
                is Result.Success -> _expensesHistoryState.value.copy(
                    transactions = result.data,
                    isLoading = false,
                    error = null
                )
                is Result.Error -> _expensesHistoryState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun setStartDate(date: LocalDate) {
        _expensesHistoryState.value = _expensesHistoryState.value.copy(startDate = date)
        tryFetchIfBothDatesSelected()
    }

    fun setEndDate(date: LocalDate) {
        _expensesHistoryState.value = _expensesHistoryState.value.copy(endDate = date)
        tryFetchIfBothDatesSelected()
    }

    private fun tryFetchIfBothDatesSelected() {
        val start = _expensesHistoryState.value.startDate
        val end = _expensesHistoryState.value.endDate

        if (start != null && end != null) {
            if (start.isAfter(end)) {
                _expensesHistoryState.value = _expensesHistoryState.value.copy(
                    error = Result.Error.CalendarError
                )
            }

            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            getAllExpensesHistory(
                startDate = start.format(formatter),
                endDate = end.format(formatter)
            )
        }
    }

    fun clearError() {
        _expensesHistoryState.value = _expensesHistoryState.value.copy(error = null)
    }
}