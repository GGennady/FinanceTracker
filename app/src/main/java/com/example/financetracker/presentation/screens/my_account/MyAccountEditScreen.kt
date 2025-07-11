package com.example.financetracker.presentation.screens.my_account

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financetracker.R
import com.example.financetracker.presentation.LocalViewModelFactory
import com.example.financetracker.presentation.components.HandleErrors
import com.example.financetracker.presentation.components.HorizontalItemWithEditText
import com.example.financetracker.presentation.components.TopBar
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountEditScreen(
    onNavigateTo: (Screen) -> Unit,
    onBackClick: () -> Unit,
    onApplyClick: (Screen) -> Unit,
) {

    val viewModel: MyAccountViewModel = viewModel(factory = LocalViewModelFactory.current)

    val myAccountState by viewModel.accountState

    val nameState = remember { mutableStateOf("") }
    val balanceState = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getAccountById()
    }

    LaunchedEffect(myAccountState.account) {
        myAccountState.account?.let { account ->
            nameState.value = account.name
            balanceState.value = account.balance
        }
    }

    LaunchedEffect(myAccountState.accountSaved) {
        if(myAccountState.accountSaved) {
            onApplyClick(Screen.MyAccount)
        }
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
                leftIcon = R.drawable.ic_cancel,
                onLeftIconClick = onBackClick,
                rightIcon = R.drawable.ic_apply,
                onRightIconClick = {
                    viewModel.putAccountByIdNameAndBalance(newName = nameState.value, newBalance = balanceState.value)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )

            HorizontalItemWithEditText(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.myAccount_accountName),
                textFieldData = nameState,
                showDivider = true,
            )

            HorizontalItemWithEditText(
                modifier = Modifier
                    .background(LightGreen)
                    .height(56.dp),
                title = stringResource(R.string.myAccount_balance),
                textFieldData = balanceState,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                showDivider = true,
            )


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