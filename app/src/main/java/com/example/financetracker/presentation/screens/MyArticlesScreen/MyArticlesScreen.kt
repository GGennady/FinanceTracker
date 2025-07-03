package com.example.financetracker.presentation.screens.MyArticlesScreen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.financetracker.presentation.components.HandleErrors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyArticlesScreen(
    onNavigateTo: (Screen) -> Unit,
    viewModel: MyArticlesViewModel = hiltViewModel()
) {

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

            HorizontalItem(
                modifier = Modifier
                    .background(surfaceContainerHigh)
                    .height(56.dp),
                title = stringResource(R.string.myArticles_search),
                titleColor = onSurfaceVariant,
                icon = R.drawable.ic_search,
                showDivider = true,
            )

            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(myArticlesState.categories) { item ->
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
