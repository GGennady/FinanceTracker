package com.example.financetracker.presentation.screens.ExpensesScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.surface
import com.example.financetracker.ui.theme.onSurface
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.financetracker.presentation.components.HandleErrors
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val expensesTestItems = listOf(
    TransactionModel(
        0,
        AccountBriefModel(1, "Тест", "500 000", "Р"),
        CategoryModel(1, "Аренда квартиры", "📘", false),
        "100 000",
        "2025-06-18T06:58:02.182Z",
        "",
        "",
        ""
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    onNavigateTo: (Screen) -> Unit,
    viewModel: ExpensesViewModel = hiltViewModel(),
) {

    val expensesState by viewModel.expensesState

    // not working because of api backend issues, so for testing just change LaunchedEffect(Unit):...
    //..."viewModel.getAllExpenses(startDate, endDate)" to "viewModel.getAllExpenses()"...
    val startOfDay = LocalDate.now().atStartOfDay()
    val endOfDay = startOfDay.plusDays(1).minusSeconds(1)
    val startDate = startOfDay.format(DateTimeFormatter.ISO_LOCAL_DATE)
    val endDate = endOfDay.format(DateTimeFormatter.ISO_LOCAL_DATE)
    LaunchedEffect(Unit) {
        viewModel.getAllExpenses()
    }

    HandleErrors(
        error = expensesState.error,
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
                title = stringResource(R.string.expenses_topbar),
                rightIcon = R.drawable.ic_history,
                onRightIconClick = { onNavigateTo(Screen.ExpensesHistory) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )


            val expensesOnly = expensesState.transactions.filter { !it.category.isIncome }
            val totalExpenses = expensesOnly.sumOf { transaction -> transaction.amount.toDoubleOrNull() ?: 0.0 }
            val currency = expensesOnly.firstOrNull()?.account?.currency

            val formattedTotal = "%,.2f %s".format(totalExpenses, currency)

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.expenses_sum),
                contentUpper = formattedTotal,
                showDivider = true,
            )
            
            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(expensesState.transactions.filter { !it.category.isIncome }) { item ->
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

            if (expensesState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        color = Green
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
private fun ExpensesScreenPreview() {
    ExpensesScreen ( { } )
}