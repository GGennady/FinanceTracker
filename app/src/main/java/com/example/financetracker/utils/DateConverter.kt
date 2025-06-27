package com.example.financetracker.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

/**
 * Utility object for formatting ISO date strings.
 */
object DateConverter {

    private val defaultFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd MMM yyyy", Locale("ru", "RU"))

    fun formatIsoDate(
        isoDate: String,
        formatter: DateTimeFormatter = defaultFormatter
    ): String? {
        return try {
            val zonedDateTime = ZonedDateTime.parse(isoDate)
            zonedDateTime.format(formatter)
        } catch (e: DateTimeParseException) {
            null // if date invalid
        }
    }
}