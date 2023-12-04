package com.example.knockoffspotify

import com.example.knockoffspotify.model.Entry
import javax.inject.Inject

class FetchAlbumsFromApi @Inject constructor(private val service: FeedApiService) {
        suspend operator fun invoke(): List<Entry> {
            val topAlbums = service.getTopAlbums()
            return topAlbums.entry
    }
}