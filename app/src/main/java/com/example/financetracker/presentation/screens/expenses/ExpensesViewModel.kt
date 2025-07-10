package com.example.financetracker.presentation.screens.expenses

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.domain.Result
import com.example.financetracker.domain.FinanceRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the ExpenseScreen logic.
 *
 * Fetches transactions from the repository and shows UI state.
 *
 * @property repository The repository providing access to data.
 */
class ExpensesViewModel @Inject constructor(private val repository: FinanceRepository): ViewModel() {

    private val _expensesState = mutableStateOf(ExpensesUIState(isLoading = true))
    val expensesState: State<ExpensesUIState> = _expensesState

    fun getAllExpenses(startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            _expensesState.value = _expensesState.value.copy(isLoading = true)

            val result = repository.getAllTransactions(
                startDate = startDate,
                endDate = endDate
            )

            _expensesState.value = when (result) {
                is Result.Success -> _expensesState.value.copy(
                    transactions = result.data,
                    isLoading = false,
                    error = null
                )
                is Result.Error -> _expensesState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun clearError() {
        _expensesState.value = _expensesState.value.copy(error = null)
    }
}

