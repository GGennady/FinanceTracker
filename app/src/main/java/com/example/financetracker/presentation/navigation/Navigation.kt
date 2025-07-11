package com.example.financetracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.financetracker.presentation.navigation.graphs.ExpensesGraph
import com.example.financetracker.presentation.navigation.graphs.expensesGraph
import com.example.financetracker.presentation.navigation.graphs.incomeGraph
import com.example.financetracker.presentation.navigation.graphs.myAccountGraph
import com.example.financetracker.presentation.screens.my_articles.MyArticlesScreen
import com.example.financetracker.presentation.screens.SettingsScreen
import com.example.financetracker.presentation.screens.add_or_edit_transaction.AddOrEditTransactionScreen
import kotlinx.serialization.Serializable

/**
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

    @Serializable
    data object MyAccountEditScreen: Screen()

    @Serializable
    data class AddOrEditTransactionScreen(
        val mode: TransactionMode,
        val type: TransactionType,
        val transactionId: Int? = null
    ) : Screen()
}

@Serializable
enum class TransactionMode {
    CREATE, EDIT
}

@Serializable
enum class TransactionType {
    INCOME, EXPENSES
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ExpensesGraph
    ) {
        expensesGraph(navController)

        incomeGraph(navController)

        myAccountGraph(navController)

        composable<Screen.MyArticles> {
            MyArticlesScreen { screen -> navController.navigate(screen) }
        }

        composable<Screen.Settings> {
            SettingsScreen { screen -> navController.navigate(screen) }
        }

        composable<Screen.AddOrEditTransactionScreen> { backStackEntry ->

            val args = backStackEntry.toRoute<Screen.AddOrEditTransactionScreen>()

            AddOrEditTransactionScreen(
                onNavigateTo = { screen -> navController.navigate(screen) },
                onBackClick = { navController.popBackStack() },
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
    }
}
