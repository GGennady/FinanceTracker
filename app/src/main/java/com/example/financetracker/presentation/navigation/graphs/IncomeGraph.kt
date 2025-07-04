package com.example.financetracker.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.presentation.screens.Income.IncomeScreen
import com.example.financetracker.presentation.screens.income_history.IncomeHistoryScreen
import kotlinx.serialization.Serializable

@Serializable
data object IncomeGraph

fun NavGraphBuilder.incomeGraph(navController: NavController) {
    navigation<IncomeGraph>(startDestination = Screen.Income) {
        composable<Screen.Income> {
            IncomeScreen( { screen -> navController.navigate(screen) } )
        }
        composable<Screen.IncomeHistory> {
            IncomeHistoryScreen(
                onNavigateTo = { screen -> navController.navigate(screen) },
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}