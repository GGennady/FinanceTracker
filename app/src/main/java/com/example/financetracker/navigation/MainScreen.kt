package com.example.financetracker.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.financetracker.R

val bottomBarItems = listOf(
    BottomBarItem(Screen.Expenses, "Expenses", R.drawable.ic_expenses),
    BottomBarItem(Screen.Income, "Income", R.drawable.ic_income),
    BottomBarItem(Screen.MyAccount, "MyAccount", R.drawable.ic_myaccount),
    BottomBarItem(Screen.MyArticles, "MyArticles", R.drawable.ic_myarticles),
    BottomBarItem(Screen.Settings, "Settings", R.drawable.ic_settings)
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, items = bottomBarItems)
        }
    ) { padding ->
        Navigation(
            modifier = Modifier
                .padding(padding)
                .systemBarsPadding(),
            navController = navController,
        )
    }
}