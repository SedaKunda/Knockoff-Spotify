package com.example.knockoffspotify.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Converts a date string to a readable format.
 * @param conversionErrorMessage: The message to display if the conversion fails.
 * @return The date in a readable format or the conversion error message.
 */

fun String.toReadableDate(conversionErrorMessage: String): String {
    return try {
        val offsetDateTime = OffsetDateTime.parse(this)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        formatter.format(offsetDateTime)
    } catch (ex: Exception) {
        conversionErrorMessage
    }
}