package com.example.financetracker.navigation

import androidx.annotation.DrawableRes
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.financetracker.ui.theme.DarkGrey
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.surfaceContainer


data class BottomBarItem(
    val screen: Screen,
    val title: String,
    @DrawableRes val iconResId: Int
)

@Composable
fun BottomBar(navController: NavController, items: List<BottomBarItem>) {

    NavigationBar(containerColor = surfaceContainer) {

        // tracking current active screen...
        // ...to know which icon on the BottomBar should be highlighted (selected).
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination

        // drawing menu
        items.forEach { item ->

            val isSelected = currentDestination?.route?.contains(item.screen::class.simpleName!!) == true

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
                    Text(item.title, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
                },

                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = DarkGrey,
                    selectedIconColor = Green,
                    unselectedTextColor = DarkGrey,
                    selectedTextColor = DarkGrey,
                    indicatorColor = LightGreen
                ),
            )
        }
    }
}