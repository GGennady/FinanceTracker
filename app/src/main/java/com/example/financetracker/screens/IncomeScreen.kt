package com.example.financetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.components.HorizontalItem
import com.example.financetracker.components.PlusFloatingActionButton
import com.example.financetracker.components.TopBar
import com.example.financetracker.models.AccountBriefModel
import com.example.financetracker.models.CategoryModel
import com.example.financetracker.models.TransactionModel
import com.example.financetracker.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.surface
import com.example.financetracker.ui.theme.onSurface

val incomeTestItems = listOf(
    TransactionModel(
        0,
        AccountBriefModel(1, "Тест", "500 000", "Р"),
        CategoryModel(1, "Акции", "📘", true),
        "100 000",
        "",
        "",
        "",
        ""
    ),
    TransactionModel(
        0,
        AccountBriefModel(1, "Тест", "500 000", "Р"),
        CategoryModel(1, "Акции", "📘", true),
        "100 000",
        "",
        "",
        "",
        ""
    ),
    TransactionModel(
        0,
        AccountBriefModel(1, "Тест", "500 000", "Р"),
        CategoryModel(1, "Акции", "📘", true),
        "100 000",
        "",
        "",
        "",
        ""
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    onNavigateTo: (Screen) -> Unit,
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
                title = "Доходы сегодня",
                clickableIcon = R.drawable.ic_history,
                onIconClick = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = "Всего",
                contentUpper = "600 000 Р",
                showDivider = true,
            )

            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(incomeTestItems) { item ->
                    HorizontalItem(
                        modifier = Modifier.height(70.dp),
                        title = item.categoryId.name,
                        contentUpper = item.amount + " " + item.account.currency,
                        icon = R.drawable.ic_arrow_detail,
                        showDivider = true
                    )
                }
            }
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
private fun IncomeScreenPreview() {
    IncomeScreen {  }
}