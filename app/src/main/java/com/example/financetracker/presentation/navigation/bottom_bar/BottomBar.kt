package com.example.financetracker.presentation.navigation.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.financetracker.R
import com.example.financetracker.presentation.navigation.graphs.ExpensesGraph
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.presentation.navigation.graphs.IncomeGraph
import com.example.financetracker.presentation.navigation.graphs.MyAccountGraph
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.onSurfaceVariant
import com.example.financetracker.ui.theme.surfaceContainer

@Composable
fun BottomBar(navController: NavController) {

    val bottomNavDestinations = listOf(
        BottomBarItem(ExpensesGraph, "Расходы", R.drawable.ic_expenses),
        BottomBarItem(IncomeGraph, "Доходы", R.drawable.ic_income),
        BottomBarItem(MyAccountGraph, "Счет", R.drawable.ic_myaccount),
        BottomBarItem(Screen.MyArticles, "Статьи", R.drawable.ic_myarticles),
        BottomBarItem(Screen.Settings, "Настройки", R.drawable.ic_settings),
    )

    NavigationBar(containerColor = surfaceContainer) {

        // tracking current active screen...
        // ...to know which icon on the BottomBar should be highlighted (selected).
        // read current destination from current backStackEntry
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination


        // iterate through the list of possible destinations
        bottomNavDestinations.forEach { destination ->

            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(destination.route::class)
            } == true

            NavigationBarItem(

                // check if destination matches currentDestination.hierarchy
                selected = isSelected,

                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        painter = painterResource(destination.iconResId),
                        contentDescription = destination.title,
                    )
                },

                label = {
                    Text(
                        text = destination.title,
                        style = Typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
                },

                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = onSurfaceVariant,
                    selectedIconColor = Green,
                    unselectedTextColor = onSurfaceVariant,
                    selectedTextColor = onSurfaceVariant,
                    indicatorColor = LightGreen
                ),
            )
        }
    }
}