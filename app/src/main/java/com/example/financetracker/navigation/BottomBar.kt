package com.example.financetracker.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.financetracker.ui.theme.White


data class BottomBarItem(
    val screen: Screen,
    val title: String,
    val iconResId: Int
)

@Composable
fun BottomBar(navController: NavController, items: List<BottomBarItem>) {

    NavigationBar(containerColor = White) {

        // tracking current active screen...
        // ...to know which icon on the BottomBar should be highlighted (selected).
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination

        // drawing menu
        items.forEach { item ->
            val isSelected = currentDestination?.route == item.screen.toString()
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.iconResId),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.screen) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}