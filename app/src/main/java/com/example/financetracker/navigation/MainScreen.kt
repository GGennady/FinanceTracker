package com.example.financetracker.navigation

import android.annotation.SuppressLint
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, items = bottomBarItems)
        }
    ) {
        Navigation(navController = navController, modifier = Modifier)
    }
}