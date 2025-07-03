package com.example.financetracker.presentation.components

import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.financetracker.utils.Result

@Composable
fun HandleErrors(
    error: Result.Error?,
    onErrorHandled: () -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    //val context = LocalContext.current

    LaunchedEffect(error) {
        error?.let {
            val message = when (it) {
                is Result.Error.BadRequest -> "Error 400: Bad Request"
                is Result.Error.Unauthorized -> "Error 401: Unauthorized"
                is Result.Error.NotFound -> "Error 404: Not Found"
                is Result.Error.InternalServerError -> "Error 500: Internal Server Error"
                is Result.Error.CalendarError -> "Error: start date is after than end date"
                is Result.Error.OfflineError -> "Error: You are offline"
                else -> "Unknown error"
            }

            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            snackbarHostState.showSnackbar(message)

            onErrorHandled()
        }
    }

}