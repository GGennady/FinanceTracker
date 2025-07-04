package com.example.financetracker.presentation.navigation.bottom_bar

import androidx.annotation.DrawableRes

data class BottomBarItem<T : Any>(
    val route: T,
    val title: String,
    @DrawableRes val iconResId: Int
)
