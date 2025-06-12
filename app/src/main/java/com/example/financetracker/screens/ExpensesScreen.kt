package com.example.financetracker.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.financetracker.R
import com.example.financetracker.components.PlusFloatingActionButton
import com.example.financetracker.components.TopBar
import com.example.financetracker.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.onSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    onNavigateTo: (Screen) -> Unit,
) {
    Scaffold(

        topBar = {
            TopBar(
                title = "Расходы сегодня",
                clickableIcon = R.drawable.ic_history,
                onIconClick = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface
                )
            )
        },

        floatingActionButton = {
            PlusFloatingActionButton {  }
        }

    ) { padding ->
        Text("test", modifier = Modifier.padding(padding))
    }
}

@Composable
@Preview
private fun ExpensesScreenPreview() {
    ExpensesScreen {  }
}