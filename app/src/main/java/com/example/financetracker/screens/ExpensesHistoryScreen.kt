package com.example.financetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.components.HorizontalItem
import com.example.financetracker.components.TopBar
import com.example.financetracker.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.outlineVariant
import com.example.financetracker.ui.theme.surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesHistoryScreen(
    onNavigateTo: (Screen) -> Unit,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surface),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(surface)
        ) {
            TopBar(
                title = "Моя история",
                rightIcon = R.drawable.ic_analysis,
                onRightIconClick = {},
                leftIcon = R.drawable.ic_back,
                onLeftIconClick = onBackClick,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = "Начало",
                contentUpper = "date",
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = outlineVariant
            )

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = "Конец",
                contentUpper = "date",
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = outlineVariant
            )

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = "Сумма",
                contentUpper = "sum ",
                showDivider = true,
            )

            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(expensesTestItems) { item ->
                    HorizontalItem(
                        modifier = Modifier.height(70.dp),
                        emoji = item.categoryId.emoji,
                        title = item.categoryId.name,
                        contentUpper = item.amount + " " + item.account.currency,
                        contentLower = item.transactionDate,
                        icon = R.drawable.ic_arrow_detail,
                        showDivider = true,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun ExpensesHistoryScreen() {
    ExpensesHistoryScreen(onBackClick = {}, onNavigateTo = {})
}
