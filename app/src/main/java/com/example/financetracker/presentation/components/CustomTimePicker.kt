package com.example.financetracker.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.financetracker.ui.theme.Black
import com.example.financetracker.ui.theme.Green
import java.time.LocalTime

@Composable
fun CustomTimePicker(
    initialTime: LocalTime = LocalTime.now(),
    onTimeSelected: (LocalTime) -> Unit,
    onDismiss: () -> Unit
) {
    var hour by remember { mutableIntStateOf(initialTime.hour) }
    var minute by remember { mutableStateOf(initialTime.minute) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выберите время") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TimeInputField(
                    label = "Часы",
                    value = hour.toString().padStart(2, '0'),
                    onValueChange = { new ->
                        new.toIntOrNull()?.let {
                            if (it in 0..23) hour = it
                        }
                    }
                )
                TimeInputField(
                    label = "Минуты",
                    value = minute.toString().padStart(2, '0'),
                    onValueChange = { new ->
                        new.toIntOrNull()?.let {
                            if (it in 0..59) minute = it
                        }
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onTimeSelected(LocalTime.of(hour, minute))
                onDismiss()
            }) {
                Text("ОК", color = Black)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена", color = Black)
            }
        },
    )
}

@Composable
private fun TimeInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    val customTextSelectionColors = TextSelectionColors(
        handleColor = Green,
        backgroundColor = Green.copy(alpha = 0.4f)
    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.width(120.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Green.copy(alpha = 0.6f),
                focusedBorderColor = Green,
                cursorColor = Green,
                focusedLabelColor = Green,
            )
        )
    }
}