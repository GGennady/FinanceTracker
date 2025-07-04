package com.example.financetracker.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.financetracker.ui.theme.Black
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import com.example.financetracker.ui.theme.Subtitile
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.outlineVariant

@Composable
fun HorizontalItemWithEditText(
    modifier: Modifier = Modifier,
    emoji: String? = null,
    title: String,
    titleColor: Color = Black,
    subtitle: String? = null,
    icon: Int? = null,
    showDivider: Boolean = false,
    onClick: (() -> Unit)? = null,
    textFieldData: MutableState<String>,
    keyboardOptions: KeyboardOptions? = null,

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        // emoji
        if (emoji != null) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp)
                    .background(color = LightGreen, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = emoji,
                )
            }
        }

        // title & subtitle
        Column(
            modifier = Modifier

                .padding(end = 12.dp),
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

        // contentUpper = TextField
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(end = if (icon != null) 16.dp else 0.dp),
        ) {

            val customTextSelectionColors = TextSelectionColors(
                handleColor = Green,
                backgroundColor = Green.copy(alpha = 0.4f)
            )
            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                OutlinedTextField(
                    value = textFieldData.value,
                    onValueChange = { textFieldData.value = it },
                    textStyle = Typography.bodyMedium,
                    keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Green,
                        cursorColor = Green,
                        focusedLabelColor = Green,
                    )
                )
            }
        }


        // icon
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Icon",
            )
        }
    }

    if (showDivider) {
        HorizontalDivider(
            thickness = 1.dp,
            color = outlineVariant
        )
    }
}