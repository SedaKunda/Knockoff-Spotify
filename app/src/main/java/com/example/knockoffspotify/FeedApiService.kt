package com.example.knockoffspotify

import com.example.knockoffspotify.model.Feed
import retrofit2.http.GET

interface FeedApiService {

    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): Feed
}
