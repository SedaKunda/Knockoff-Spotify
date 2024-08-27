package com.example.knockoffspotify.data.remote

import com.example.knockoffspotify.data.model.Album
import com.example.knockoffspotify.data.model.TopAlbums
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumsApiService {

    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): TopAlbums

    @GET("lookup")
    suspend fun getAlbumDetails(
        @Query("id") id: String,
        @Query("entity") entity: String = "song"
    ): Album
}