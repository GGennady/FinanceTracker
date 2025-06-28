package com.example.financetracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.financetracker.presentation.screens.ExpensesHistoryScreen.ExpensesHistoryScreen
import com.example.financetracker.presentation.screens.ExpensesScreen.ExpensesScreen
import com.example.financetracker.presentation.screens.IncomeHistoryScreen.IncomeHistoryScreen
import com.example.financetracker.presentation.screens.IncomeScreen.IncomeScreen
import com.example.financetracker.presentation.screens.MyAccountScreen.MyAccountScreen
import com.example.financetracker.presentation.screens.MyArticlesScreen.MyArticlesScreen
import com.example.financetracker.presentation.screens.SettingsScreen
import kotlinx.serialization.Serializable

/**
 * Navigation destinations.
 *
 * Represents each screen in the app using a sealed class hierarchy for type-safe navigation.
 */
sealed class Screen {
    @Serializable
    data object Expenses: Screen()

    @Serializable
    data object Income: Screen()

    @Serializable
    data object MyAccount: Screen()

    @Serializable
    data object MyArticles: Screen()

    @Serializable
    data object Settings: Screen()

    @Serializable
    data object ExpensesHistory: Screen()

    @Serializable
    data object IncomeHistory: Screen()
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Expenses
    ) {
        composable<Screen.Expenses> {
            ExpensesScreen( { screen -> navController.navigate(screen) } )
        }
        composable<Screen.Income> {
            IncomeScreen( { screen -> navController.navigate(screen) } )
        }
        composable<Screen.MyAccount> {
            MyAccountScreen ( { screen -> navController.navigate(screen) } )
        }
        composable<Screen.MyArticles> {
            MyArticlesScreen ( { screen -> navController.navigate(screen) } )
        }
        composable<Screen.Settings> {
            SettingsScreen { screen -> navController.navigate(screen) }
        }
        composable<Screen.ExpensesHistory> {
            ExpensesHistoryScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateTo = { screen -> navController.navigate(screen) },
            )
        }
        composable<Screen.IncomeHistory> {
            IncomeHistoryScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateTo = { screen -> navController.navigate(screen) },
            )
        }
    }
}