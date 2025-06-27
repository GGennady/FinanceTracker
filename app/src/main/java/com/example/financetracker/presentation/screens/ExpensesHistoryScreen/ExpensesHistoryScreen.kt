package com.example.financetracker.presentation.screens.ExpensesHistoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financetracker.R
import com.example.financetracker.presentation.components.HorizontalItem
import com.example.financetracker.presentation.components.TopBar
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.outlineVariant
import com.example.financetracker.ui.theme.surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.financetracker.DateConverter
import com.example.financetracker.presentation.components.CustomDatePicker
import com.example.financetracker.presentation.components.HandleErrors
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesHistoryScreen(
    onNavigateTo: (Screen) -> Unit,
    onBackClick: () -> Unit,
    viewModel: ExpensesHistoryViewModel = hiltViewModel(),
) {

    val expensesHistoryState by viewModel.expensesHistoryState

    val startDate = expensesHistoryState.startDate
    val endDate = expensesHistoryState.endDate

    LaunchedEffect(key1 = startDate, key2 = endDate) {
        if (startDate == null && endDate == null) {
            viewModel.getAllExpensesHistory()
        }
        if (startDate != null && endDate != null) {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            viewModel.getAllExpensesHistory(
                startDate = startDate.format(formatter),
                endDate = endDate.format(formatter)
            )
        }
    }

    HandleErrors(
        error = expensesHistoryState.error,
        onErrorHandled = { viewModel.clearError() }
    )

    var showStartPicker by remember { mutableStateOf(false) }
    var showEndPicker by remember { mutableStateOf(false) }

    if (showStartPicker) {
        CustomDatePicker(
            initialDate = startDate ?: LocalDate.now(),
            onDateSelected = { viewModel.setStartDate(it) },
            onDismiss = { showStartPicker = false }
        )
    }
    if (showEndPicker) {
        CustomDatePicker(
            initialDate = endDate ?: LocalDate.now(),
            onDateSelected = { viewModel.setEndDate(it) },
            onDismiss = { showEndPicker = false }
        )
    }

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
                title = stringResource(R.string.expensesHistory_topbar),
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
                title = stringResource(R.string.expensesHistory_startDate),
                contentUpper = startDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) ?: stringResource(R.string.historyScreens_chooseDate),
                icon = R.drawable.ic_arrow_detail,
                onClick = { showStartPicker = true },
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = outlineVariant
            )

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.expensesHistory_endDate),
                contentUpper = endDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) ?: stringResource(R.string.historyScreens_chooseDate),
                icon = R.drawable.ic_arrow_detail,
                onClick = { showEndPicker = true },
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = outlineVariant
            )

            val expensesHistoryOnly = expensesHistoryState.transactions.filter { !it.category.isIncome }
            val totalExpensesHistory =  expensesHistoryOnly.sumOf { transaction -> transaction.amount.toDoubleOrNull() ?: 0.0 }
            val currency =  expensesHistoryOnly.firstOrNull()?.account?.currency

            val formattedTotal = "%,.2f %s".format(totalExpensesHistory, currency)

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.expensesHistory_sum),
                contentUpper = formattedTotal,
                showDivider = true,
            )

            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(expensesHistoryState.transactions.filter { !it.category.isIncome }) { item ->
                    HorizontalItem(
                        modifier = Modifier.height(70.dp),
                        emoji = item.category.emoji,
                        title = item.category.name,
                        subtitle = item.comment,
                        contentUpper = "${item.amount} ${item.account.currency}",
                        contentLower = DateConverter.formatIsoDate(item.transactionDate) ?: "DateTimeParseException",
                        icon = R.drawable.ic_arrow_detail,
                        showDivider = true,
                    )
                }
            }
        }

        if (expensesHistoryState.isLoading) {
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
}
