package com.example.financetracker.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financetracker.domain.models.Category
import com.example.financetracker.presentation.navigation.TransactionType
import com.example.financetracker.presentation.screens.add_or_edit_transaction.AddOrEditTransactionViewModel
import com.example.financetracker.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryBottomSheet(
    type: TransactionType,
    onCategorySelected: (Category) -> Unit,
    onDismiss: () -> Unit,
    viewModel: AddOrEditTransactionViewModel
) {


    val state by viewModel.transactionState

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var categories by remember { mutableStateOf<List<Category>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.getAllArticles()
    }

    val filteredCategories = state.categories.filter {
        when(type) {
            TransactionType.EXPENSES -> !it.isIncome
            TransactionType.INCOME -> it.isIncome
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    color = Green,
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 1.dp)
            ) {
                items(filteredCategories) { item ->
                    HorizontalItem(
                        modifier = Modifier.fillMaxWidth().height(70.dp),
                        emoji = item.emoji,
                        title = item.name,
                        showDivider = true,
                        onClick = {
                            onCategorySelected(item)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}