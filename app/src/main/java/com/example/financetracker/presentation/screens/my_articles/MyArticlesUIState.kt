package com.example.financetracker.presentation.screens.my_articles

import com.example.financetracker.domain.models.Category
import com.example.financetracker.utils.Result

/**
 * UI state data class for the My Articles screen.
 *
 * Represents the loading state, list of available categories, and possible error.
 *
 * @property isLoading Indicates whether category data is currently being loaded.
 * @property categories The list of available article categories.
 * @property error The occurred error during data fetching (possibly).
 */
data class MyArticlesUIState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: Result.Error? = null,
)
