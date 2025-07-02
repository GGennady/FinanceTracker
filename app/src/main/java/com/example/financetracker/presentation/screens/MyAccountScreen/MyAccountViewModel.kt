package com.example.financetracker.presentation.screens.MyAccountScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.utils.Result
import com.example.financetracker.domain.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the MyAccountScreen logic.
 *
 * Fetches transactions from the repository and shows UI state.
 *
 * @property repository The repository providing access to data.
 */
@HiltViewModel
class MyAccountViewModel @Inject constructor(private val repository: FinanceRepository) : ViewModel() {

    private val _accountState = mutableStateOf(MyAccountUIState(isLoading = true))
    val accountState: State<MyAccountUIState> = _accountState

    fun getAccountById() {
        viewModelScope.launch {
            _accountState.value = _accountState.value.copy(isLoading = true)

            val result = repository.getAccountById()

            _accountState.value = when (result) {
                is Result.Success -> _accountState.value.copy(
                    account = result.data,
                    isLoading = false,
                    error = null
                )
                is Result.Error -> _accountState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun clearError() {
        _accountState.value = _accountState.value.copy(error = null)
    }
}