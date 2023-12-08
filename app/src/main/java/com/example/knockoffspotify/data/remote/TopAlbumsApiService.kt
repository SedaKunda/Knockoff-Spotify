package com.example.knockoffspotify.data.remote

import com.example.knockoffspotify.model.TopAlbums
import retrofit2.http.GET

interface TopAlbumsApiService {

    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): TopAlbums
}
