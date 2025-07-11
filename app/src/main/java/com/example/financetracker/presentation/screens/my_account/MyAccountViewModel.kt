package com.example.financetracker.presentation.screens.my_account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.domain.FinanceRepository
import com.example.financetracker.domain.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the MyAccountScreen logic.
 *
 * Fetches transactions from the repository and shows UI state.
 *
 * @property repository The repository providing access to data.
 */
class MyAccountViewModel @Inject constructor(private val repository: FinanceRepository) : ViewModel() {

    private val _accountState = mutableStateOf(MyAccountUIState(isLoading = true))
    val accountState: State<MyAccountUIState> = _accountState

    private var lastFun: (() -> Unit)? = null

    fun retryLastFun(){
        lastFun?.invoke()
    }

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

    fun putAccountByIdNameAndBalance(newName: String, newBalance: String) {
        viewModelScope.launch {
            _accountState.value = _accountState.value.copy(isLoading = true)

            val currency = accountState.value.account?.currency ?: return@launch

            val result = repository.putAccountById(newName, newBalance, currency)

            lastFun = { putAccountByIdNameAndBalance(newName, newBalance) }

            _accountState.value = when (result) {
                is Result.Success -> _accountState.value.copy(
                    accountAfterPut = result.data,
                    accountSaved = true,
                    isLoading = false,
                    error = null,
                )

                is Result.Error -> _accountState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun putAccountByIdCurrency(newCurrency: String) {
        viewModelScope.launch {
            _accountState.value = _accountState.value.copy(isLoading = true)

            val name = accountState.value.account?.name ?: return@launch
            val balance = accountState.value.account?.balance ?: return@launch

            val result = repository.putAccountById(name, balance, newCurrency)

            _accountState.value = when (result) {
                is Result.Success -> _accountState.value.copy(
                    accountAfterPut = result.data,
                    isLoading = false,
                    error = null,
                )

                is Result.Error -> _accountState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
            if (result is Result.Success) {
                getAccountById()
            }
        }
    }

    fun clearError() {
        _accountState.value = _accountState.value.copy(error = null)
    }
}