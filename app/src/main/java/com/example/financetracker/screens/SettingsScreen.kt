package com.example.financetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.components.HorizontalItem
import com.example.financetracker.components.TopBar
import com.example.financetracker.navigation.Screen
import com.example.financetracker.ui.theme.Black
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.surface
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.outlineVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateTo: (Screen) -> Unit,
) {
    var isDarkTheme by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surface),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(surface)
        ) {
            TopBar(
                title = "Настройки",
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green,
                    titleContentColor = onSurface,
                ),
            )

            // theme switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    text ="Темная тема",
                    style = Typography.bodyLarge,
                    color = Black,
                )

                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { isDarkTheme = it },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Green,
                    )
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = outlineVariant
            )

            HorizontalItem(
                modifier = Modifier
                    .height(56.dp),
                title = "Основной экран",
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .height(56.dp),
                title = "Звуки",
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .height(56.dp),
                title = "Хаптики",
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .height(56.dp),
                title = "Код пароль",
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .height(56.dp),
                title = "Синхронизация",
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .height(56.dp),
                title = "Язык",
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .height(56.dp),
                title = "О программе",
                icon = R.drawable.ic_arrow_detail_v2,
                showDivider = true,
            )
        }
    }
}

@Composable
@Preview
private fun SettingsScreenPreview() {
    SettingsScreen {  }
}
