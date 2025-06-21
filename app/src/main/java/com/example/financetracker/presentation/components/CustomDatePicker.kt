package com.example.financetracker.presentation.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.financetracker.R
import com.example.financetracker.ui.theme.Black
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.LightGreen
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    initialDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val selectedDate = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        onDateSelected(selectedDate)
                    }
                    onDismiss()
                }
            ) {
                Text(color = Black, text = stringResource(R.string.text1_customDatePicker))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(color = Black, text = stringResource(R.string.text2_customDatePicker))
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = LightGreen,
        ),
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                containerColor = LightGreen,
                selectedDayContainerColor = Green,
                titleContentColor = Black,
                headlineContentColor = Black,
                weekdayContentColor = Black,
                subheadContentColor = Black,
                navigationContentColor = Black,
                yearContentColor = Black,
                disabledYearContentColor = Black.copy(alpha = 0.38f),
                currentYearContentColor = Black,
                selectedYearContentColor = Black,
                disabledSelectedYearContentColor = Black.copy(alpha = 0.38f),
                selectedYearContainerColor = Green,
                disabledSelectedYearContainerColor = Green.copy(alpha = 0.2f),
                dayContentColor = Black,
                disabledDayContentColor = Black.copy(alpha = 0.38f),
                selectedDayContentColor = Black,
                disabledSelectedDayContentColor = Black.copy(alpha = 0.38f),
                disabledSelectedDayContainerColor = Green.copy(alpha = 0.2f),
                todayContentColor = Black,
                todayDateBorderColor = Black,
                dayInSelectionRangeContentColor = Black,
                dayInSelectionRangeContainerColor = Green.copy(alpha = 0.2f),
                dividerColor = Green,
                dateTextFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = Black,
                    unfocusedTextColor = Black,
                    disabledTextColor = Black.copy(alpha = 0.38f),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Black,
                    focusedIndicatorColor = Black,
                    unfocusedIndicatorColor = Black,
                    disabledIndicatorColor = Black.copy(alpha = 0.38f)
                )

            ),
        )
    }
}