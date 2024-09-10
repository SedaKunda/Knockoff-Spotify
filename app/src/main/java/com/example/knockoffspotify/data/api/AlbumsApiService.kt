package com.example.knockoffspotify.data.api

import com.example.knockoffspotify.data.model.Album
import com.example.knockoffspotify.data.model.TopAlbums
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service to fetch albums data
 */
interface AlbumsApiService {

    /**
     * Fetches top albums from iTunes API
     */
    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): TopAlbums

    /**
     * Fetches album details from iTunes API
     * @param id album id
     * @param entity entity type to fetch - album or song
     */
    @GET("lookup")
    suspend fun getAlbumDetails(
        @Query("id") id: String,
        @Query("entity") entity: String = "song"
    ): Album
}