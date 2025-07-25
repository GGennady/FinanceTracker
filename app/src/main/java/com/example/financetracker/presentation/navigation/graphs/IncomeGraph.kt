package com.example.financetracker.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.presentation.screens.Income.IncomeScreen
import com.example.financetracker.presentation.screens.add_or_edit_transaction.AddOrEditTransactionScreen
import com.example.financetracker.presentation.screens.analysis.AnalysisScreen
import com.example.financetracker.presentation.screens.income_history.IncomeHistoryScreen
import kotlinx.serialization.Serializable

@Serializable
data object IncomeGraph

fun NavGraphBuilder.incomeGraph(navController: NavController) {
    navigation<IncomeGraph>(startDestination = Screen.Income) {
        composable<Screen.Income> {
            IncomeScreen { screen -> navController.navigate(screen) }
        }
        composable<Screen.IncomeHistory> {
            IncomeHistoryScreen(
                onNavigateTo = { screen -> navController.navigate(screen) },
                onBackClick = { navController.popBackStack() },
            )
        }
        composable<Screen.AddOrEditTransactionScreen> { backStackEntry ->

            val args = backStackEntry.toRoute<Screen.AddOrEditTransactionScreen>()

            AddOrEditTransactionScreen(
                onNavigateTo = { screen -> navController.navigate(screen) },
                onBackClick = { navController.popBackStack(args, true) },
                onApplyClick = { screen ->
                    navController.navigate(screen) {
                        popUpTo(Screen.AddOrEditTransactionScreen) {
                            inclusive = true
                        }
                    }
                },
                mode = args.mode,
                type = args.type,
                transactionId = args.transactionId,
            )
        }
        composable<Screen.AnalysisScreen> { backStackEntry ->

            val args = backStackEntry.toRoute<Screen.AnalysisScreen>()

            AnalysisScreen(
                onNavigateTo = { screen -> navController.navigate(screen) },
                onBackClick = { navController.popBackStack() },
                type = args.type,
            )
        }
    }
}