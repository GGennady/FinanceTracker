package com.example.financetracker.data

import android.util.Log
import com.example.financetracker.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Base class for handling API responses with retry and error handling logic.
 *
 * Provides a safeApiCall wrapper to standardize error mapping and retries for network calls.
 *
 * @property networkMonitor Used to check network availability before making a request.
 */
abstract class BaseApiResponse(protected val networkMonitor: NetworkMonitor) {

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
    /**
     * Executes an API call with automatic retry and error handling.
     *
     * @param call The suspending function making the API call.
     * @param retryCount Number of retry attempts for retryable errors.
     * @param delayMillSec Delay in milliseconds between retry attempts.
     * @return Result sealed class.
     */
    suspend fun <T> safeApiCall(
        call: suspend () -> T,
        retryCount: Int = 3,
        delayMillSec: Long = 2000,
    ): Result<T> = withContext(Dispatchers.IO) {

        if (!networkMonitor.hasNetworkConnection()) {
            return@withContext Result.Error.OfflineError
        }

        var currentTry = 1

        while (currentTry <= retryCount) {
            try {
                return@withContext Result.Success(call())
            } catch (e: HttpException) {
                val isLastTry = currentTry == retryCount
                val shouldRetry = e.code() in retryableStatusCodes
                if (!shouldRetry || isLastTry) {
                    return@withContext httpCodeToResult(e.code())
                }
                delay(delayMillSec)
                Log.e("ApiCallRetry", "Error from retryableStatusCodes")
            } catch (e: IOException) {
                return@withContext Result.Error.OfflineError
            } catch (e: Exception) {
                return@withContext Result.Error.OtherErrors
            }

            currentTry++
        }
        Result.Error.OtherErrors
    }
}