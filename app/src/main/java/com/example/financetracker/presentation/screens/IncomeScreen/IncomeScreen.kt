package com.example.financetracker.presentation.screens.IncomeScreen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financetracker.R
import com.example.financetracker.presentation.components.HorizontalItem
import com.example.financetracker.presentation.components.PlusFloatingActionButton
import com.example.financetracker.presentation.components.TopBar
import com.example.financetracker.data.api.model.AccountBriefModel
import com.example.financetracker.data.api.model.CategoryModel
import com.example.financetracker.data.api.model.TransactionModel
import com.example.financetracker.presentation.components.HandleErrors
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.surface
import com.example.financetracker.ui.theme.onSurface
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    onNavigateTo: (Screen) -> Unit,
    viewModel: IncomeViewModel = hiltViewModel(),
) {

    val incomeState by viewModel.incomeState

    // not working because of api backend issues, so for testing just change LaunchedEffect(Unit):...
    //..."viewModel.getAllExpenses(startDate, endDate)" to "viewModel.getAllExpenses()"...
    val startOfDay = LocalDate.now().atStartOfDay()
    val endOfDay = startOfDay.plusDays(1).minusSeconds(1)
    val startDate = startOfDay.format(DateTimeFormatter.ISO_LOCAL_DATE)
    val endDate = endOfDay.format(DateTimeFormatter.ISO_LOCAL_DATE)
    LaunchedEffect(Unit) {
        viewModel.getAllIncome()
    }

    HandleErrors(
        error = incomeState.error,
        onErrorHandled = { viewModel.clearError() }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(surface)
        ) {
            TopBar(
                title = stringResource(R.string.income_topbar),
                rightIcon = R.drawable.ic_history,
                onRightIconClick = { onNavigateTo(Screen.IncomeHistory) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )

            val incomeOnly = incomeState.transactions.filter { it.category.isIncome }
            val totalIncome = incomeOnly.sumOf { transaction -> transaction.amount.toDoubleOrNull() ?: 0.0 }
            val currency = incomeOnly.firstOrNull()?.account?.currency

            val formattedTotal = "%,.2f %s".format(totalIncome, currency)

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.income_sum),
                contentUpper = formattedTotal,
                showDivider = true,
            )

            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(incomeState.transactions.filter { it.category.isIncome }) { item ->
                    HorizontalItem(
                        modifier = Modifier.height(70.dp),
                        emoji = item.category.emoji,
                        title = item.category.name,
                        subtitle = item.comment,
                        contentUpper = "${item.amount} ${item.account.currency}",
                        icon = R.drawable.ic_arrow_detail,
                        showDivider = true,
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
    IncomeScreen ({  })
}