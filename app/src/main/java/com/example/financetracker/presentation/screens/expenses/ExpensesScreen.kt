package com.example.financetracker.presentation.screens.expenses

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financetracker.R
import com.example.financetracker.presentation.LocalViewModelFactory
import com.example.financetracker.presentation.components.HandleErrors
import com.example.financetracker.presentation.components.HorizontalItem
import com.example.financetracker.presentation.components.PlusFloatingActionButton
import com.example.financetracker.presentation.components.TopBar
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.presentation.navigation.TransactionMode
import com.example.financetracker.presentation.navigation.TransactionType
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.surface
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    onNavigateTo: (Screen) -> Unit,
) {

    val viewModel: ExpensesViewModel = viewModel(factory = LocalViewModelFactory.current)

    val expensesState by viewModel.expensesState

    val startOfDay = LocalDate.now().atStartOfDay()
    val endOfDay = startOfDay.plusDays(1).minusSeconds(1)
    val startDate = startOfDay.format(DateTimeFormatter.ISO_LOCAL_DATE)
    val endDate = endOfDay.format(DateTimeFormatter.ISO_LOCAL_DATE)
    LaunchedEffect(Unit) {
        viewModel.getAllExpenses(startDate, endDate)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    HandleErrors(
        error = expensesState.error,
        onErrorHandled = { viewModel.clearError() },
        snackbarHostState = snackbarHostState
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
            val currency = expensesOnly.firstOrNull()?.account?.currency ?: ""

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
                        onClick = { onNavigateTo(Screen.AddOrEditTransactionScreen(mode = TransactionMode.EDIT, type = TransactionType.EXPENSES, transactionId = item.id)) },
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
            onClick = { onNavigateTo(Screen.AddOrEditTransactionScreen(mode = TransactionMode.CREATE, type = TransactionType.EXPENSES)) }
        )

        // snackbar
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            SnackbarHost(hostState = snackbarHostState)
        }
    }
}
