package com.example.financetracker.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.financetracker.ui.theme.Black
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.outlineVariant

@Composable
fun HorizontalItemWithEditText(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = Black,
    showDivider: Boolean = false,
    textFieldData: MutableState<String>,
    keyboardOptions: KeyboardOptions? = null,

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        // title
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
        }

        // contentUpper = TextField
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
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
                        unfocusedBorderColor = outlineVariant,
                        focusedBorderColor = Green,
                        cursorColor = Green,
                        focusedLabelColor = Green,
                    )
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