package com.example.knockoffspotify.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toReadableDate(): String {
    val yearMonthFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return if (this != "") {
        val parsedDate = yearMonthFormat.parse(this)
        val dayMonthFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        parsedDate.let { dayMonthFormat.format(parsedDate) }
    } else "Unknown release date"
}