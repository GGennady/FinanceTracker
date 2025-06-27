package com.example.financetracker.presentation.screens.IncomeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.Result
import com.example.financetracker.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    private val _incomeState = mutableStateOf(IncomeUIState(isLoading = true))
    val incomeState: State<IncomeUIState> = _incomeState

    fun getAllIncome(startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            _incomeState.value = _incomeState.value.copy(isLoading = true)

            val result = repository.getAllTransactions(
                accountId = 21,
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