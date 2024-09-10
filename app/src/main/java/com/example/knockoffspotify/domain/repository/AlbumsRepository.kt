package com.example.knockoffspotify.domain.repository

import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.domain.model.TopAlbum

/**
    * This interface defines the methods that the AlbumsRepository should implement.
 */
interface AlbumsRepository {

    /**
        * This method should return a list of TopAlbums.
     */
    suspend fun getTopAlbums(): List<TopAlbum>

    /**
        * This method should return an Album object given an id.
        * @param id: The id of the album to be fetched.
     */
    suspend fun getAlbumDetails(id: String): Album?
}