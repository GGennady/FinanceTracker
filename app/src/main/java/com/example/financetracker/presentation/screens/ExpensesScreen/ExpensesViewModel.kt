package com.example.financetracker.presentation.screens.ExpensesScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.data.Result
import com.example.financetracker.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    private val _expensesState = mutableStateOf(ExpensesUIState(isLoading = true))
    val expensesState: State<ExpensesUIState> = _expensesState

    fun getAllExpenses(startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            _expensesState.value = _expensesState.value.copy(isLoading = true)

            val result = repository.getAllTransactions(
                accountId = 21,
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

