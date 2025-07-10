package com.example.financetracker.presentation.screens.Income

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.domain.Result
import com.example.financetracker.domain.FinanceRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the IncomeScreen logic.
 *
 * Fetches transactions from the repository and shows UI state.
 *
 * @property repository The repository providing access to data.
 */
class IncomeViewModel @Inject constructor(private val repository: FinanceRepository): ViewModel() {

    private val _incomeState = mutableStateOf(IncomeUIState(isLoading = true))
    val incomeState: State<IncomeUIState> = _incomeState

    fun getAllIncome(startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            _incomeState.value = _incomeState.value.copy(isLoading = true)

            val result = repository.getAllTransactions(
                startDate = startDate,
                endDate = endDate
            )

            _incomeState.value = when (result) {
                is Result.Success -> _incomeState.value.copy(
                    transactions = result.data,
                    isLoading = false,
                    error = null
                )
                is Result.Error -> _incomeState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun clearError() {
        _incomeState.value = _incomeState.value.copy(error = null)
    }

}