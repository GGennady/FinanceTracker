package com.example.financetracker.data

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()

    sealed class Error: Result<Nothing>() {
        // 400
        data object BadRequest: Error()
        // 401
        data object Unauthorized: Error()
        // 404
        data object NotFound: Error()
        // 500
        data object InternalServerError: Error()

        // Other
        data object OtherErrors: Error()

        // offline
        data object OfflineError: Error()
    }
}