package com.example.financetracker.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.ui.theme.Black
import com.example.financetracker.ui.theme.Dismiss
import com.example.financetracker.ui.theme.Subtitile
import com.example.financetracker.ui.theme.Transparent
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.White
import com.example.financetracker.ui.theme.onSurface
import com.example.financetracker.ui.theme.outlineVariant

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
        Column {

            HorizontalBottomSheetItem(
                modifier = Modifier
                    .background(Transparent)
                    .height(56.dp),
                title = stringResource(R.string.currencyBottomSheet_rub),
                icon = R.drawable.ic_rub,
                onClick = { onCurrencySelected("RUB") },
                showDivider = true,
            )

            HorizontalBottomSheetItem(
                modifier = Modifier
                    .background(Transparent)
                    .height(56.dp),
                title = stringResource(R.string.currencyBottomSheet_usd),
                icon = R.drawable.ic_usd,
                onClick = { onCurrencySelected("USD") },
                showDivider = true,
            )

            HorizontalBottomSheetItem(
                modifier = Modifier
                    .background(Transparent)
                    .height(56.dp),
                title = stringResource(R.string.currencyBottomSheet_eur),
                icon = R.drawable.ic_eur,
                onClick = { onCurrencySelected("EUR") },
                showDivider = true,
            )

            HorizontalBottomSheetItem(
                modifier = Modifier
                    .background(Dismiss)
                    .height(56.dp),
                title = stringResource(R.string.currencyBottomSheet_dismiss),
                icon = R.drawable.ic_dismiss,
                iconTint = White,
                titleColor = White,
                onClick = onDismiss,
            )

        }
    }
}

@Composable
fun HorizontalBottomSheetItem(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = Black,
    subtitle: String? = null,
    @DrawableRes icon: Int? = null,
    iconTint: Color = onSurface,
    showDivider: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        // icon
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Icon",
                tint = iconTint,
            )
        }

        // title & subtitle
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                style = Typography.bodyLarge,
                color = titleColor,
            )
            if (subtitle != null && subtitle != "") {
                Text(
                    text = subtitle,
                    style = Typography.bodyMedium,
                    color = Subtitile,
                )
            }
        }
    }
    if (showDivider) {
        HorizontalDivider(
            thickness = 1.dp,
            color = outlineVariant
        )
    }
}