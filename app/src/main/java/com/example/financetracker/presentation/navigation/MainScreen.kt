package com.example.financetracker.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.financetracker.presentation.navigation.bottom_bar.BottomBar
import com.example.financetracker.ui.theme.surface

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomBar(navController = navController)
        },
        containerColor = surface,


    ) { innerPadding ->

        val filteredPadding = PaddingValues(
            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
            bottom = innerPadding.calculateBottomPadding(),
            top = 0.dp
        )

        Navigation(
            modifier = Modifier.padding(filteredPadding),
            navController = navController,
        )
    }
}