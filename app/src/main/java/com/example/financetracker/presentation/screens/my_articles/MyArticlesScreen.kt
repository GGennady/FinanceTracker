package com.example.financetracker.presentation.screens.my_articles

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.presentation.components.HorizontalItem
import com.example.financetracker.presentation.components.TopBar
import com.example.financetracker.presentation.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.surface
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.onSurfaceVariant
import com.example.financetracker.ui.theme.surfaceContainerHigh
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financetracker.presentation.LocalViewModelFactory
import com.example.financetracker.presentation.components.HandleErrors
import com.example.financetracker.presentation.components.HorizontalItemWithEditText
import com.example.financetracker.presentation.screens.expenses.ExpensesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyArticlesScreen(
    onNavigateTo: (Screen) -> Unit,
) {

    val viewModel: MyArticlesViewModel = viewModel(factory = LocalViewModelFactory.current)

    val myArticlesState by viewModel.articlesState

    LaunchedEffect(Unit) {
        viewModel.getAllArticles()
    }

    val snackbarHostState = remember { SnackbarHostState() }
    HandleErrors(
        error = myArticlesState.error,
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
                title = stringResource(R.string.myArticles_topbar),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )

            val searchQuery = viewModel.searchQuery
            HorizontalItemWithEditText(
                modifier = Modifier
                    .background(surfaceContainerHigh)
                    .height(56.dp),
                title = stringResource(R.string.myArticles_search),
                titleColor = onSurfaceVariant,
                textFieldData = searchQuery,
                showDivider = true,
            )

            val filteredCategories = viewModel.getFilteredCategories()
            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(filteredCategories) { item ->
                    HorizontalItem(
                        modifier = Modifier.height(70.dp),
                        emoji = item.emoji,
                        title = item.name,
                        showDivider = true
                    )
                }
            }

            if (myArticlesState.isLoading) {
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
