package com.example.financetracker.presentation.screens.MyAccountScreen

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
class MyAccountViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _accountState = mutableStateOf(MyAccountUIState(isLoading = true))
    val accountState: State<MyAccountUIState> = _accountState

    fun getAccountById(id: Int) {
        viewModelScope.launch {
            _accountState.value = _accountState.value.copy(isLoading = true)

            val result = repository.getAccountById(id)

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