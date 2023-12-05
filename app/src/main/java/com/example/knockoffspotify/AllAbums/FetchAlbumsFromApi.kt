package com.example.knockoffspotify.AllAbums

import com.example.knockoffspotify.data.remote.FeedApiService
import com.example.knockoffspotify.model.Entry
import javax.inject.Inject

class FetchAlbumsFromApi @Inject constructor(private val service: FeedApiService) {
        suspend operator fun invoke(): List<Entry> {
            val feed = service.getTopAlbums().feed
            return feed.entry
    }
}