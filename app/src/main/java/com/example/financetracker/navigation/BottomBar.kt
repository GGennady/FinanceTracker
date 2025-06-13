package com.example.financetracker.navigation

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
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.onSurfaceVariant
import com.example.financetracker.ui.theme.surfaceContainer

@Composable
fun BottomBar(navController: NavController, items: List<BottomBarItem>) {

    NavigationBar(containerColor = surfaceContainer) {

        // tracking current active screen...
        // ...to know which icon on the BottomBar should be highlighted (selected).
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination

        // drawing menu
        items.forEach { item ->

            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(item.screen::class)
            } == true
            //val isSelected = currentDestination?.route?.contains(item.screen::class.simpleName!!) == true

            NavigationBarItem(

                selected = isSelected,

                onClick = {
                    navController.navigate(item.screen) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        painter = painterResource(item.iconResId),
                        contentDescription = item.title,
                    )
                },

                label = {
                    Text(
                        text = item.title,
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