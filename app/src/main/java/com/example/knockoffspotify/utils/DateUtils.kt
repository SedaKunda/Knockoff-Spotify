package com.example.knockoffspotify.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Checks if the string is a valid date and converts it to a readable format.
 * @param conversionErrorMessage: The message to display if the conversion fails.
 * @return The date in a readable format or the conversion error message.
 */
@SuppressLint("ConstantLocale")
val locale: Locale = Locale.getDefault()

val yearMonthFormat = SimpleDateFormat("yyyy-MM-dd", locale)
val dayMonthFormat = SimpleDateFormat("dd.MM.yyyy", locale)

fun String.toReadableDate(conversionErrorMessage: String): String {
    return if (this.isValid()) {
        val parsedDate = yearMonthFormat.parse(this)
        parsedDate?.let { dayMonthFormat.format(parsedDate) } ?: conversionErrorMessage
    } else conversionErrorMessage
}

private fun String.isValid(): Boolean {
    return if (this != "" && !this.matches("\\d{4}-[01]\\d-[0-3]\\d.*".toRegex())) false else try {
        yearMonthFormat.parse(this)
        true
    } catch (ex: ParseException) {
        false
    }
}