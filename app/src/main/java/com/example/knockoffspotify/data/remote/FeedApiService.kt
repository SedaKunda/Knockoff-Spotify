package com.example.knockoffspotify.data.remote

import com.example.knockoffspotify.model.Albums
import retrofit2.http.GET

interface FeedApiService {

    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): Albums
}
