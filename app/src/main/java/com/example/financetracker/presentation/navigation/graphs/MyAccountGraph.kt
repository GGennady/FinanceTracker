package com.example.financetracker.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.presentation.screens.my_account.MyAccountEditScreen
import com.example.financetracker.presentation.screens.my_account.MyAccountScreen
import kotlinx.serialization.Serializable

@Serializable
data object MyAccountGraph

fun NavGraphBuilder.myAccountGraph(navController: NavController) {
    navigation<MyAccountGraph>(startDestination = Screen.MyAccount) {
        composable<Screen.MyAccount> {
            MyAccountScreen( { screen -> navController.navigate(screen) } )
        }
        composable<Screen.MyAccountEditScreen> {
            MyAccountEditScreen(
                onNavigateTo = { screen -> navController.navigate(screen) },
                onBackClick = { navController.popBackStack() },
                onApplyClick = { screen ->
                    navController.navigate(screen) {
                        popUpTo(Screen.MyAccountEditScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}