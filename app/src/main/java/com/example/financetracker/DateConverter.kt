package com.example.financetracker

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

object DateConverter {

    private val defaultFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd MMM yyyy", Locale("ru", "RU"))

    fun formatIsoDate(
        isoDate: String,
        formatter: DateTimeFormatter = defaultFormatter
    ): String {
        return try {
            val zonedDateTime = ZonedDateTime.parse(isoDate)
            zonedDateTime.format(formatter)
        } catch (e: DateTimeParseException) {
            isoDate // if date invalid
        }
    }
}