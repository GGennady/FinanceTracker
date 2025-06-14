package com.example.financetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.example.financetracker.models.AccountModel
import com.example.financetracker.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.surface
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.outlineVariant

val accountTestItem = AccountBriefModel(1, "Основной счет","-670 000", "Р")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountScreen(
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
                title = "Мой счет",
                clickableIcon = R.drawable.ic_edit,
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
                title = "Баланс",
                emoji = "💰",
                contentUpper = accountTestItem.balance,
                icon =  R.drawable.ic_arrow_detail,
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = outlineVariant
            )

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = "Валюта",
                contentUpper = accountTestItem.currency,
                icon =  R.drawable.ic_arrow_detail,
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
private fun MyAccountScreenPreview() {
    MyAccountScreen {  }
}