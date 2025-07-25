package com.example.financetracker.presentation.screens.analysis

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financetracker.R
import com.example.financetracker.domain.models.TransactionResponse
import com.example.financetracker.presentation.LocalViewModelFactory
import com.example.financetracker.presentation.components.CustomDatePicker
import com.example.financetracker.presentation.components.HandleErrors
import com.example.financetracker.presentation.components.HorizontalItem
import com.example.financetracker.presentation.components.TopBar
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.presentation.navigation.TransactionType
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.White
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.surface
import com.example.graphs.circle_graph.CircleGraph
import com.example.graphs.circle_graph.CircleGraphElement
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisScreen(
    onNavigateTo: (Screen) -> Unit,
    onBackClick: () -> Unit,
    type: TransactionType,
) {

    val viewModel: AnalysisViewModel = viewModel(factory = LocalViewModelFactory.current)

    val analysisState by viewModel.analysisState

    val startDate = analysisState.startDate
    val endDate = analysisState.endDate

    LaunchedEffect(key1 = startDate, key2 = endDate) {
        if (startDate == null && endDate == null) {
            viewModel.getAllTransactionsHistory()
        }
        if (startDate != null && endDate != null) {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            viewModel.getAllTransactionsHistory(
                startDate = startDate.format(formatter),
                endDate = endDate.format(formatter)
            )
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    HandleErrors(
        error = analysisState.error,
        onErrorHandled = { viewModel.clearError() },
        snackbarHostState = snackbarHostState
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
                title = stringResource(R.string.analysis_topbar),
                leftIcon = R.drawable.ic_back,
                onLeftIconClick = onBackClick,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                    titleContentColor = onSurface,
                ),
            )

            HorizontalItem(
                modifier = Modifier
                    .background(White)
                    .height(56.dp),
                title = stringResource(R.string.expensesHistory_startDate),
                contentUpper = startDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) ?: stringResource(R.string.historyScreens_chooseDate),
                icon = R.drawable.ic_arrow_detail,
                showDivider = true,
                onClick = { showStartPicker = true },
            )

            HorizontalItem(
                modifier = Modifier
                    .background(White)
                    .height(56.dp),
                title = stringResource(R.string.expensesHistory_endDate),
                contentUpper = endDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) ?: stringResource(R.string.historyScreens_chooseDate),
                icon = R.drawable.ic_arrow_detail,
                showDivider = true,
                onClick = { showEndPicker = true },
            )

            val filteredTransactions = when(type) {
                TransactionType.EXPENSES -> {
                    analysisState.transactions.filter { !it.category.isIncome }
                }

                TransactionType.INCOME -> {
                    analysisState.transactions.filter { it.category.isIncome }
                }
            }
            val totalHistory =  filteredTransactions.sumOf { transaction -> transaction.amount.toDoubleOrNull() ?: 0.0 }
            val currency =  filteredTransactions.firstOrNull()?.account?.currency ?: ""
            val formattedTotal = "%,.2f %s".format(totalHistory, currency)

            HorizontalItem(
                modifier = Modifier
                    .background(White)
                    .height(56.dp),
                title = stringResource(R.string.expensesHistory_sum),
                contentUpper = formattedTotal,
                showDivider = true,
            )

            fun mapTransactionsToPieChartEntries(transactions: List<TransactionResponse>): List<CircleGraphElement> {

                val filteredTransactions = when(type) {
                    TransactionType.EXPENSES -> {
                        transactions.filter { !it.category.isIncome }.groupBy { it.category }
                    }

                    TransactionType.INCOME -> {
                        transactions.filter { it.category.isIncome }.groupBy { it.category }
                    }
                }

                val total = filteredTransactions.values
                    .flatten()
                    .sumOf { it.amount.toDoubleOrNull() ?: 0.0 }

                val colorPalette = listOf(
                    Color(0xFFE57373), Color(0xFFBA68C8), Color(0xFF64B5F6), Color(0xFF4DB6AC),
                    Color(0xFFFFD54F), Color(0xFFA1887F), Color(0xFF90A4AE), Color(0xFFFF8A65),
                    Color(0xFF81C784), Color(0xFFFFB74D), Color(0xFF9575CD), Color(0xFF7986CB),
                )

                var colorIndex = 0

                val elements = filteredTransactions.map { (category, items) ->
                    val sum = items.sumOf { it.amount.toDoubleOrNull()?: 0.0 }
                    val percent = if (total == 0.0) 0f else ((sum / total) * 100).toFloat()
                    CircleGraphElement(
                        label = "${category.emoji} ${category.name}",
                        percentage = percent,
                        color = colorPalette[colorIndex++ % colorPalette.size]
                    )
                }.sortedByDescending { it.percentage }

                return elements
            }

            val elements = remember(analysisState.transactions) { mapTransactionsToPieChartEntries(analysisState.transactions) }
            CircleGraph(elements)

            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(analysisState.transactions
                    .filter {
                        when(type) {
                            TransactionType.EXPENSES -> {
                                !it.category.isIncome
                            }
                            TransactionType.INCOME -> {
                                it.category.isIncome
                            }
                        }
                    }
                    .sortedByDescending { it.transactionDate }
                ) { item ->
                    HorizontalItem(
                        modifier = Modifier.height(70.dp),
                        emoji = item.category.emoji,
                        title = item.category.name,
                        subtitle = item.comment,
                        contentUpper = "${item.amount} ${item.account.currency}",
                        contentLower = String.format("%.1f%%", (item.amount.toDoubleOrNull() ?: 0.0) / totalHistory * 100),
                        showDivider = true,
                    )
                }
            }

            if (analysisState.isLoading) {
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

        // snackbar
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            SnackbarHost(hostState = snackbarHostState)
        }
    }
}