package com.example.financetracker.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.presentation.screens.expenses.ExpensesScreen
import com.example.financetracker.presentation.screens.expenses_history.ExpensesHistoryScreen
import kotlinx.serialization.Serializable

@Serializable
data object ExpensesGraph

fun NavGraphBuilder.expensesGraph(navController: NavController) {
    navigation<ExpensesGraph>(startDestination = Screen.Expenses) {
        composable<Screen.Expenses> {
            ExpensesScreen( { screen -> navController.navigate(screen) } )
        }
        composable<Screen.ExpensesHistory> {
            ExpensesHistoryScreen(
                onNavigateTo = { screen -> navController.navigate(screen) },
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}