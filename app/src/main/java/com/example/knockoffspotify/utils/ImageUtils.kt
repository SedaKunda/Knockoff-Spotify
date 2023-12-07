package com.example.knockoffspotify.utils

import com.example.knockoffspotify.model.ImImage

fun List<ImImage>.extractImage(): String? {
    return try {
        this.last().label
    }
    catch (e: Exception) {
        null
    }
}
