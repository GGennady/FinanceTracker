package com.example.financetracker.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.financetracker.R

val bottomBarItems = listOf(
    BottomBarItem(Screen.Expenses, "Расходы", R.drawable.ic_expenses),
    BottomBarItem(Screen.Income, "Доходы", R.drawable.ic_income),
    BottomBarItem(Screen.MyAccount, "Счет", R.drawable.ic_myaccount),
    BottomBarItem(Screen.MyArticles, "Статьи", R.drawable.ic_myarticles),
    BottomBarItem(Screen.Settings, "Настройки", R.drawable.ic_settings)
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, items = bottomBarItems)
        }
    ) {
        Navigation(
            modifier = Modifier
                .systemBarsPadding(),
            navController = navController,
        )
    }
}