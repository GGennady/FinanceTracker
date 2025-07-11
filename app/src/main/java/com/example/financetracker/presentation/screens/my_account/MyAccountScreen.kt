package com.example.financetracker.presentation.screens.my_account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financetracker.R
import com.example.financetracker.presentation.LocalViewModelFactory
import com.example.financetracker.presentation.components.CurrencyBottomSheet
import com.example.financetracker.presentation.components.HandleErrors
import com.example.financetracker.presentation.components.HorizontalItem
import com.example.financetracker.presentation.components.TopBar
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountScreen(
    onNavigateTo: (Screen) -> Unit,
) {

    val viewModel: MyAccountViewModel = viewModel(factory = LocalViewModelFactory.current)

    val myAccountState by viewModel.accountState

    LaunchedEffect(Unit) {
        viewModel.getAccountById()
    }

    val snackbarHostState = remember { SnackbarHostState() }
    HandleErrors(
        error = myAccountState.error,
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
                title = stringResource(R.string.myAccount_topbar),
                rightIcon = R.drawable.ic_edit,
                onRightIconClick = { onNavigateTo(Screen.MyAccountEditScreen) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.myAccount_accountName),
                contentUpper = myAccountState.account?.name,
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.myAccount_balance),
                emoji = "💰",
                contentUpper = myAccountState.account?.balance,
                showDivider = true,
            )

            var showCurrencyBottomSheet by remember { mutableStateOf(false) }

            HorizontalItem(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.myAccount_currency),
                contentUpper = myAccountState.account?.currency,
                icon =  R.drawable.ic_arrow_detail,
                onClick = { showCurrencyBottomSheet = true },
                showDivider = true,
            )

            if (showCurrencyBottomSheet) {
                CurrencyBottomSheet(
                    onCurrencySelected = { selectedOne ->
                        viewModel.putAccountByIdCurrency(selectedOne)
                        showCurrencyBottomSheet = false
                    },
                    onDismiss = { showCurrencyBottomSheet = false },
                )
            }

            if (myAccountState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        color = Green,
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
