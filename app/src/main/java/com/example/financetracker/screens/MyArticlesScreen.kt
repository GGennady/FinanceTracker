package com.example.financetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.components.HorizontalItem
import com.example.financetracker.components.TopBar
import com.example.financetracker.models.CategoryModel
import com.example.financetracker.navigation.Screen
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.surface
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.onSurfaceVariant
import com.example.financetracker.ui.theme.outlineVariant
import com.example.financetracker.ui.theme.surfaceContainerHigh

val myArticlesTestItems = listOf(
    CategoryModel(id = 1, name = "КатегориНейм", emoji = "📘", false),
    CategoryModel(id = 1, name = "КатегориНейм", emoji = "📘", false),
    CategoryModel(id = 1, name = "КатегориНейм", emoji = "📘", false),
    CategoryModel(id = 1, name = "КатегориНейм", emoji = "📘", false),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyArticlesScreen(
    onNavigateTo: (Screen) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surface),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(surface)
        ) {
            TopBar(
                title = "Мои статьи",
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )

            HorizontalItem(
                modifier = Modifier
                    .background(surfaceContainerHigh)
                    .height(56.dp),
                title = "Найти статью",
                titleColor = onSurfaceVariant,
                icon = R.drawable.ic_search,
                showDivider = true,
            )

            LazyColumn (
                contentPadding = PaddingValues(bottom = 1.dp) // to show last divider
            ){
                items(myArticlesTestItems) { item ->
                    HorizontalItem(Modifier.height(70.dp), title = item.name, emoji = item.emoji, showDivider = true)
                }
            }
        }
    }
}

@Composable
@Preview
private fun MyArticlesScreenPreview() {
    MyArticlesScreen {  }
}