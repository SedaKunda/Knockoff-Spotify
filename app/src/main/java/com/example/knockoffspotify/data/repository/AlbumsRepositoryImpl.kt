package com.example.knockoffspotify.data.repository

import com.example.knockoffspotify.data.api.AlbumsApiService
import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.domain.model.Song
import com.example.knockoffspotify.domain.model.TopAlbum
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.extractImage
import com.example.knockoffspotify.utils.toReadableDate
import javax.inject.Inject
import com.example.knockoffspotify.data.model.Song as SongApi
import com.example.knockoffspotify.data.model.TopAlbums as TopAlbumsApi

class AlbumsRepositoryImpl @Inject constructor(
    private val service: AlbumsApiService
) : AlbumsRepository {
    override suspend fun getTopAlbums(): List<TopAlbum> {
        val topAlbums = service.getTopAlbums()

        return topAlbums.toTopAlbumDomain()
    }

    override suspend fun getAlbumDetails(id: String): Album? {
        val albumDetails = service.getAlbumDetails(id)

        val (album, songs) = albumDetails.results.partition { it.collectionType == "Album" }
        val albumResult = album.firstOrNull()?.let {
            it.toAlbumDomain().copy(
                songs = songs.map { song -> song.toSongDomain() }
            )
        }
        return albumResult
    }

    private fun SongApi.toSongDomain(): Song {
        return Song(
            trackName = trackName ?: "",
            artistName = artistName ?: "",
            primaryGenreName = primaryGenreName ?: "",
            collectionExplicitness = collectionExplicitness ?: "",
            artwork = artworkUrl100 ?: artworkUrl60 ?: artworkUrl30 ?: ""
        )
    }

    private fun SongApi.toAlbumDomain(): Album {
        return Album(
            albumName = collectionName ?: "",
            artistName = artistName ?: "",
            genre = primaryGenreName ?: "",
            explicitness = collectionExplicitness ?: "",
            artwork = artworkUrl100 ?: artworkUrl60 ?: artworkUrl30 ?: "",
            songs = emptyList()
        )
    }

    private fun TopAlbumsApi.toTopAlbumDomain(): List<TopAlbum> {
        return feed.topAlbums.map {
            TopAlbum(
                id = it.id.attributes.imId,
                name = it.name.label,
                artist = it.artist.label,
                releaseDate = it.releaseDate.label.toReadableDate("Unknown release date"),
                imageUrl = it.image.extractImage() ?: ""
            )
        }
    }
}