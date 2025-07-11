package com.example.financetracker.presentation.components

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.financetracker.domain.Result

@Composable
fun HandleErrors(
    error: Result.Error?,
    onErrorHandled: () -> Unit,
    snackbarHostState: SnackbarHostState,
    isNeedRetry: (() -> Unit)? = null,
) {

    LaunchedEffect(error) {
        error?.let {
            val (message, showBtn) = when (it) {
                is Result.Error.BadRequest -> "Error 400: Bad Request" to false
                is Result.Error.Unauthorized -> "Error 401: Unauthorized" to false
                is Result.Error.NotFound -> "Error 404: Not Found" to false
                is Result.Error.InternalServerError -> "Error 500: Internal Server Error" to true
                is Result.Error.CalendarError -> "Error: start date is after than end date" to false
                is Result.Error.OfflineError -> "Error: You are offline" to false
                else -> "Unknown error" to false
            }

            val finalMessage = when(showBtn) {
                true -> snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = "Повторить"
                )
                false -> snackbarHostState.showSnackbar(message)
            }

            if (finalMessage == SnackbarResult.ActionPerformed && showBtn) {
                isNeedRetry?.invoke()
                Log.e("ErrorTest", isNeedRetry.toString())
            }

            onErrorHandled()
        }
    }
}