package com.example.financetracker.presentation.screens.my_articles

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financetracker.data.api.model.CategoryModel
import com.example.financetracker.utils.Result
import com.example.financetracker.domain.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the MyArticlesScreen logic.
 *
 * Fetches transactions from the repository and shows UI state.
 *
 * @property repository The repository providing access to data.
 */
@HiltViewModel
class MyArticlesViewModel @Inject constructor(private val repository: FinanceRepository): ViewModel() {

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

    var searchQuery = mutableStateOf("")

    fun getFilteredCategories(): List<CategoryModel> {
        return _articlesState.value.categories.filter {
            it.name.contains(searchQuery.value, ignoreCase = true)
        }
    }

    fun clearError() {
        _articlesState.value = _articlesState.value.copy(error = null)
    }
}