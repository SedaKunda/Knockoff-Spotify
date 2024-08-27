package com.example.knockoffspotify.utils

import com.example.knockoffspotify.data.model.AlbumImage

fun List<AlbumImage>.extractImage(): String? {
    return try {
        this.last().label
    }
    catch (e: Exception) {
        null
    }
}
