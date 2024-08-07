package com.example.knockoffspotify.data.remote

import com.example.knockoffspotify.model.Album
import com.example.knockoffspotify.model.TopAlbums
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumsApiService {

    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): TopAlbums

    @GET("lookup?id={id}&entity=song")
    suspend fun getAlbumDetails(@Path("id") id: String): Album //todo: change to AlbumDetails
}