package com.example.financetracker.presentation.screens.add_or_edit_transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.domain.FinanceRepository
import com.example.financetracker.domain.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddOrEditTransactionViewModel @Inject constructor(private val repository: FinanceRepository): ViewModel() {

    private val _transactionState = mutableStateOf(AddOrEditUIState(isLoading = true))

    val transactionState: State<AddOrEditUIState> = _transactionState

    fun getTransactionById(id: Int) {
        viewModelScope.launch {
            _transactionState.value = _transactionState.value.copy(isLoading = true)

            val result = repository.getTransactionById(id)

            _transactionState.value = when(result) {
                is Result.Success -> _transactionState.value.copy(
                    transaction = result.data,
                    isLoading = false,
                    error = null
                )
                is Result.Error -> _transactionState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun getAllArticles() {
        viewModelScope.launch {
            _transactionState.value = _transactionState.value.copy(isLoading = true)

            val result = repository.getAllCategories()

            _transactionState.value = when (result) {
                is Result.Success -> _transactionState.value.copy(
                    categories = result.data,
                    isLoading = false,
                    error = null,
                )

                is Result.Error -> _transactionState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun getAccountById() {
        viewModelScope.launch {
            _transactionState.value = _transactionState.value.copy(isLoading = true)

            val result = repository.getAccountById()

            _transactionState.value = when (result) {
                is Result.Success -> _transactionState.value.copy(
                    account = result.data,
                    isLoading = false,
                    error = null
                )
                is Result.Error -> _transactionState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun putTransaction(id: Int, accountId: Int, categoryId: Int, amount: String, transactionDate: String, comment: String?) {
        viewModelScope.launch {
            _transactionState.value = _transactionState.value.copy(isLoading = true)

            val result = repository.putTransaction(id, accountId, categoryId, amount, transactionDate, comment)

            _transactionState.value = when (result) {
                is Result.Success -> _transactionState.value.copy(
                    transaction = result.data,
                    transactionSaved = true,
                    isLoading = false,
                    error = null,
                )

                is Result.Error -> _transactionState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun postTransaction(accountId: Int, categoryId: Int, amount: String, transactionDate: String, comment: String?) {
        viewModelScope.launch {
            _transactionState.value = _transactionState.value.copy(isLoading = true)

            val result = repository.postTransaction(accountId, categoryId, amount, transactionDate, comment)

            _transactionState.value = when (result) {
                is Result.Success -> _transactionState.value.copy(
                    transactionPost = result.data,
                    transactionSaved = true,
                    isLoading = false,
                    error = null,
                )

                is Result.Error -> _transactionState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun clearError() {
        _transactionState.value = _transactionState.value.copy(error = null)
    }
}