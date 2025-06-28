package com.example.financetracker.utils

/**
 * A custom Result sealed class representing the result of a request.
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()

    sealed class Error: Result<Nothing>() {

        /**
         * Bad request (HTTP 400): usually due to invalid parameters or malformed input.
         */
        data object BadRequest: Error()

        /**
         * Unauthorized (HTTP 401): request requires valid authentication.
         */
        data object Unauthorized: Error()

        /**
         * Not found (HTTP 404): requested resource could not be found.
         */
        data object NotFound: Error()

        /**
         * Internal server error (HTTP 500): server failed to process the request.
         */
        data object InternalServerError: Error()

        /**
         * No internet connection detected (by NetworkMonitor).
         */
        data object OfflineError: Error()

        /**
         * Error related to calendar data or date operations (for presentation/components/CustomDatePicker.kt).
         */
        data object CalendarError: Error()

        /**
         * Other errors.
         */
        data object OtherErrors: Error()
    }
}