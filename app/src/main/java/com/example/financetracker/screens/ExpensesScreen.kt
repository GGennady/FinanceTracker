package com.example.financetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.components.ListItem
import com.example.financetracker.components.PlusFloatingActionButton
import com.example.financetracker.components.TopBar
import com.example.financetracker.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.Surface
import com.example.financetracker.ui.theme.onSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    onNavigateTo: (Screen) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Surface)
        ) {
            TopBar(
                title = "Расходы сегодня",
                clickableIcon = R.drawable.ic_history,
                onIconClick = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )
        }

        PlusFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp),
        ) {

        }
    }
}

@Composable
@Preview
private fun ExpensesScreenPreview() {
    ExpensesScreen {  }
}