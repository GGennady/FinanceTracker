package com.example.financetracker.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.financetracker.R
import com.example.financetracker.ui.theme.surface

data class BottomBarItem(
    val screen: Screen,
    val title: String,
    @DrawableRes val iconResId: Int
)
val bottomBarItems = listOf(
    BottomBarItem(Screen.Expenses, "Расходы", R.drawable.ic_expenses),
    BottomBarItem(Screen.Income, "Доходы", R.drawable.ic_income),
    BottomBarItem(Screen.MyAccount, "Счет", R.drawable.ic_myaccount),
    BottomBarItem(Screen.MyArticles, "Статьи", R.drawable.ic_myarticles),
    BottomBarItem(Screen.Settings, "Настройки", R.drawable.ic_settings)
)


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomBar(navController = navController, items = bottomBarItems)
        },
        containerColor = surface,


    ) { innerPadding ->

        val filteredPadding = PaddingValues(
            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
            bottom = innerPadding.calculateBottomPadding(),
            top = 0.dp
        )

        Navigation(
            modifier = Modifier.padding(filteredPadding),
            navController = navController,
        )
    }
}