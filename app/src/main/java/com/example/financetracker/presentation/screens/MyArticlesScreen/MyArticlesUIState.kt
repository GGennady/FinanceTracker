package com.example.financetracker.presentation.screens.MyArticlesScreen

import com.example.financetracker.data.Result
import com.example.financetracker.data.api.model.CategoryModel

data class MyArticlesUIState(
    val isLoading: Boolean = false,
    val categories: List<CategoryModel> = emptyList(),
    val error: Result.Error? = null,
)
