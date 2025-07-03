package com.example.financetracker.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financetracker.ui.theme.Dismiss
import com.example.financetracker.ui.theme.Transparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBottomSheet(
    onCurrencySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState()
    ) {
        Column(Modifier.padding(16.dp)) {

            HorizontalItem(
                modifier = Modifier
                    .background(Transparent)
                    .height(56.dp),
                title = "Российский рубль (₽)",
                onClick = { onCurrencySelected("RUB") },
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .background(Transparent)
                    .height(56.dp),
                title = "Американский доллар ($)",
                onClick = { onCurrencySelected("USD") },
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .background(Transparent)
                    .height(56.dp),
                title = "Евро (€)",
                onClick = { onCurrencySelected("EUR") },
                showDivider = true,
            )

            HorizontalItem(
                modifier = Modifier
                    .background(Dismiss)
                    .height(56.dp),
                title = "Отмена",
                onClick = onDismiss,
                showDivider = true,
            )
        }
    }
}