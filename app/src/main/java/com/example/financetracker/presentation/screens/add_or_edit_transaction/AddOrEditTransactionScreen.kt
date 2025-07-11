package com.example.financetracker.presentation.screens.add_or_edit_transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financetracker.R
import com.example.financetracker.presentation.LocalViewModelFactory
import com.example.financetracker.presentation.components.CategoryBottomSheet
import com.example.financetracker.presentation.components.CustomDatePicker
import com.example.financetracker.presentation.components.CustomTimePicker
import com.example.financetracker.presentation.components.HandleErrors
import com.example.financetracker.presentation.components.HorizontalItem
import com.example.financetracker.presentation.components.HorizontalItemWithEditText
import com.example.financetracker.presentation.components.TopBar
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.presentation.navigation.TransactionMode
import com.example.financetracker.presentation.navigation.TransactionType
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.Transparent
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.surface
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrEditTransactionScreen(
    onNavigateTo: (Screen) -> Unit,
    onBackClick: () -> Unit,
    onApplyClick: (Screen) -> Unit,
    mode: TransactionMode,
    type: TransactionType,
    transactionId: Int? = null // null if TransactionMode == CREATE
) {

    val viewModel: AddOrEditTransactionViewModel = viewModel(factory = LocalViewModelFactory.current)

    val transactionState by viewModel.transactionState

    val sumState = remember { mutableStateOf("") }
    val categoryEmojiState = remember { mutableStateOf("") }
    val categoryState = remember { mutableStateOf("") }
    val commentState = remember { mutableStateOf("") }
    val accountIdState = remember { mutableIntStateOf(-1) }
    val categoryIdState = remember { mutableIntStateOf(-1) }

    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    val selectedTime = remember { mutableStateOf(LocalTime.now()) }

    val showDatePicker = remember { mutableStateOf(false) }
    val showTimePicker = remember { mutableStateOf(false) }

    val finalInstant = remember(selectedDate.value, selectedTime.value) {
        val zoned = ZonedDateTime.of(
            selectedDate.value,
            selectedTime.value,
            ZoneId.systemDefault()
        )
        zoned.toInstant().toString() // TransactionDate format
    }

    if(mode == TransactionMode.EDIT) {
        LaunchedEffect(transactionId, transactionState.transaction, transactionState.account) {

            viewModel.getTransactionById(transactionId!!)
            viewModel.getAccountById()

            transactionState.transaction?.let { transactionResponse ->
                sumState.value = transactionResponse.amount
                categoryState.value = transactionResponse.category.name
                categoryEmojiState.value = transactionResponse.category.emoji
                commentState.value = transactionResponse.comment.toString()
                categoryIdState.intValue = transactionResponse.category.id
            }

            transactionState.account?.let { accountResponse ->
                accountIdState.intValue = accountResponse.id
            }
        }

        LaunchedEffect(transactionState.transactionSaved) {
            if(transactionState.transactionSaved) {
                onBackClick()
            }
        }
    }

    if(mode == TransactionMode.CREATE) {
        LaunchedEffect(transactionState.account) {
            viewModel.getAccountById()

            transactionState.account?.let { accountResponse ->
                accountIdState.intValue = accountResponse.id
            }
        }

        LaunchedEffect(transactionState.transactionSaved) {
            if(transactionState.transactionSaved) {
                onBackClick()
            }
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    HandleErrors(
        error = transactionState.error,
        onErrorHandled = { viewModel.clearError() },
        snackbarHostState = snackbarHostState,
        isNeedRetry = { viewModel.retryLastFun() }
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
                title = when(mode) {
                    TransactionMode.EDIT -> "Редактировать"
                    TransactionMode.CREATE -> "Добавить"
                },
                leftIcon = R.drawable.ic_cancel,
                onLeftIconClick = onBackClick,
                rightIcon = R.drawable.ic_apply,
                onRightIconClick = {
                    when(mode) {
                        TransactionMode.EDIT -> {
                            viewModel.putTransaction(
                                id = transactionId!!,
                                accountId = accountIdState.intValue,
                                categoryId = categoryIdState.intValue,
                                amount = sumState.value,
                                comment = commentState.value,
                                transactionDate = finalInstant
                            )
                        }

                        TransactionMode.CREATE -> {
                            viewModel.postTransaction(
                                accountId = accountIdState.intValue,
                                categoryId = categoryIdState.intValue,
                                amount = sumState.value,
                                comment = commentState.value,
                                transactionDate = finalInstant
                            )
                            //onBackClick()
                            if (transactionState.transactionPost != null) {
                                onBackClick()
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )


            HorizontalItem(
                modifier = Modifier
                    .height(70.dp),
                title = "Cчёт",
                contentUpper = transactionState.account?.name,
                showDivider = true,
            )


            var showCategoryBottomSheet by remember { mutableStateOf(false) }

            when(type) {
                TransactionType.EXPENSES ->
                    HorizontalItem(
                        modifier = Modifier
                            .height(70.dp),
                        title = "Статья",
                        contentUpper = "${categoryEmojiState.value} ${categoryState.value}",
                        icon = R.drawable.ic_arrow_detail_v2,
                        showDivider = true,
                        onClick = { showCategoryBottomSheet = true },
                    )
                TransactionType.INCOME ->
                    HorizontalItem(
                        modifier = Modifier
                            .height(70.dp),
                        title = "Статья",
                        contentUpper = "${categoryEmojiState.value} ${categoryState.value}",
                        icon = R.drawable.ic_arrow_detail_v2,
                        showDivider = true,
                        onClick = { showCategoryBottomSheet = true }
                    )
            }

            if (showCategoryBottomSheet) {
                CategoryBottomSheet(
                    type = type,
                    onCategorySelected = { selectedCategory ->
                        categoryState.value = selectedCategory.name
                        categoryEmojiState.value = selectedCategory.emoji
                        categoryIdState.intValue = selectedCategory.id
                    },
                    onDismiss = { showCategoryBottomSheet = false },
                    viewModel = viewModel
                )
            }


            HorizontalItemWithEditText(
                modifier = Modifier
                    .background(Transparent)
                    .height(70.dp),
                title = "Сумма",
                textFieldData = sumState,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .height(70.dp),
                title = "Дата",
                contentUpper = selectedDate.value.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
                onClick = { showDatePicker.value = true },
            )

            HorizontalItem(
                modifier = Modifier
                    .height(70.dp),
                title = "Время",
                contentUpper = selectedTime.value.format(DateTimeFormatter.ofPattern("HH:mm")),
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
                onClick = { showTimePicker.value = true },
            )

            if (showDatePicker.value) {
                CustomDatePicker(
                    initialDate = selectedDate.value,
                    onDateSelected = {
                        selectedDate.value = it
                    },
                    onDismiss = { showDatePicker.value = false }
                )
            }

            if (showTimePicker.value) {
                CustomTimePicker(
                    initialTime = selectedTime.value,
                    onTimeSelected = {
                        selectedTime.value = it
                    },
                    onDismiss = { showTimePicker.value = false }
                )
            }


            HorizontalItemWithEditText(
                modifier = Modifier
                    .background(Transparent)
                    .height(70.dp),
                title = "Описание",
                textFieldData = commentState,
                showDivider = true,
            )
        }

        if (transactionState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    color = Green,
                )
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