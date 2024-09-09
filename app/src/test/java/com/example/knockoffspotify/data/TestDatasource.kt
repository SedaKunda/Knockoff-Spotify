package com.example.knockoffspotify.data

import com.example.knockoffspotify.data.model.AlbumImage
import com.example.knockoffspotify.data.model.AlbumName
import com.example.knockoffspotify.data.model.Artist
import com.example.knockoffspotify.data.model.Attributes
import com.example.knockoffspotify.data.model.Category
import com.example.knockoffspotify.data.model.CategoryAttributes
import com.example.knockoffspotify.data.model.Feed
import com.example.knockoffspotify.data.model.Id
import com.example.knockoffspotify.data.model.Id2
import com.example.knockoffspotify.data.model.IdAttributes
import com.example.knockoffspotify.data.model.ReleaseDate
import com.example.knockoffspotify.data.model.Song
import com.example.knockoffspotify.data.model.TopAlbum
import com.example.knockoffspotify.data.model.TopAlbums

class TestDatasource {
    companion object {
        val topAlbumsStubbed = TopAlbums(
            Feed(
                topAlbums = listOf(
                    TopAlbum(
                        name = AlbumName("1989 (Taylor's Version) [Deluxe]"),
                        artist = Artist("Taylor Swift"),
                        releaseDate = ReleaseDate("2023-10-20T00:00:00-07:00"),
                        image = listOf(
                            AlbumImage(
                                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/55x55bb.png",
                                attributes = Attributes(height = "55")
                            ),
                            AlbumImage(
                                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/60x60bb.png",
                                attributes = Attributes(height = "60")
                            ),
                            AlbumImage(
                                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/170x170bb.png",
                                attributes = Attributes(height = "170")
                            )
                        ),
                        id = Id(IdAttributes("1713845538")),
                        category = Category(CategoryAttributes("", "")),
                    ),
                    TopAlbum(
                        name = AlbumName("Red (Taylor's Version) [Deluxe]"),
                        artist = Artist("Taylor Swift"),
                        releaseDate = ReleaseDate("2023-10-20T00:00:00-07:00"),
                        image = listOf(
                            AlbumImage(
                                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/55x55bb.png",
                                attributes = Attributes(height = "55")
                            ),
                            AlbumImage(
                                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/60x60bb.png",
                                attributes = Attributes(height = "60")
                            ),
                            AlbumImage(
                                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/170x170bb.png",
                                attributes = Attributes(height = "170")
                            )
                        ),
                        id = Id(IdAttributes("1713845538")),
                        category = Category(CategoryAttributes("", "")),
                    )
                ),
                id = Id2(""),
            )
        )

        val songStubbed = Song(
            wrapperType = "Test Wrapper Type",
            kind = "Test Kind",
            artistName = "Test Artist",
            collectionName = "Test Album",
            artworkUrl100 = "https://example.com/image.jpg",
            artworkUrl60 = "https://example.com/image.jpg",
            artworkUrl30 = "https://example.com/image.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Track",
            collectionExplicitness = "explicit",
            collectionType = "Test Collection Type",
        )
    }
}