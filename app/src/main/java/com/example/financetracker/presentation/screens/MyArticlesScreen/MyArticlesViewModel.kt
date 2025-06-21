package com.example.financetracker.presentation.screens.MyArticlesScreen

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
class MyArticlesViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    private val _articlesState = mutableStateOf(MyArticlesUIState(isLoading = true))
    val articlesState: State<MyArticlesUIState> = _articlesState

    fun getAllArticles() {
        viewModelScope.launch {
            _articlesState.value = _articlesState.value.copy(isLoading = true)

            val result = repository.getAllCategories()

            _articlesState.value = when (result) {
                is Result.Success -> _articlesState.value.copy(
                    categories = result.data,
                    isLoading = false,
                    error = null,
                )

                is Result.Error -> _articlesState.value.copy(
                    isLoading = false,
                    error = result
                )
            }
        }
    }

    fun clearError() {
        _articlesState.value = _articlesState.value.copy(error = null)
    }
}