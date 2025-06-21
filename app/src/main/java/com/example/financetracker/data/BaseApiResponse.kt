package com.example.financetracker.data

import android.util.Log
import com.example.financetracker.NetworkMonitor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class BaseApiResponse() {

    private val retryableStatusCodes = setOf(500)

    private fun httpCodeToResult(code: Int): Result.Error {
        return when (code) {
            400 -> Result.Error.BadRequest
            401 -> Result.Error.Unauthorized
            404 -> Result.Error.NotFound
            500 -> Result.Error.InternalServerError
            else -> Result.Error.OtherErrors
        }
    }

    suspend fun <T> safeApiCall(
        call: suspend () -> T,
        networkMonitor: NetworkMonitor,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        retryCount: Int = 3,
        delayMillSec: Long = 2000,
    ): Result<T> = withContext(dispatcher) {

        if (!networkMonitor.hasInternetConnection()) {
            return@withContext Result.Error.OfflineError
        }

        repeat(retryCount - 1) {
            try {
                return@withContext Result.Success(call())
            } catch (e: HttpException) {
                if (e.code() in retryableStatusCodes) {
                    //Log.e("ApiError", "Error from retryableStatusCodes", e)
                    delay(delayMillSec)
                } else {
                    return@withContext httpCodeToResult(e.code())
                }
            } catch (e: Exception) {
                return@withContext Result.Error.OtherErrors
            }
        }

        // last try after retries
        try {
            Result.Success(call())
        } catch (e: IOException) {
            Result.Error.OfflineError
        } catch (e: HttpException) {
            httpCodeToResult(e.code())
        } catch (e: Exception) {
            Result.Error.OtherErrors
        }
    }
}