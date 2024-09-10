package com.example.knockoffspotify.utils

import com.example.knockoffspotify.data.model.AlbumImage

/**
 * Extracts the last image from the list of album images and returns it.
 * @return The last image from the list of album images.
 * @return null if the list is empty.
 */
fun List<AlbumImage>.extractImage(): String? {
    return try {
        this.last().label
    }
    catch (e: Exception) {
        null
    }
}
