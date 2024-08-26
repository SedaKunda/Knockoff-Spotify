package com.example.knockoffspotify.data.local

import com.example.knockoffspotify.model.Album
import com.example.knockoffspotify.model.AlbumImage
import com.example.knockoffspotify.model.AlbumName
import com.example.knockoffspotify.model.Artist
import com.example.knockoffspotify.model.Attributes
import com.example.knockoffspotify.model.Category
import com.example.knockoffspotify.model.CategoryAttributes
import com.example.knockoffspotify.model.Id
import com.example.knockoffspotify.model.IdAttributes
import com.example.knockoffspotify.model.ReleaseDate

class Datasource() {
    fun loadEntries(): List<Album> {
        return listOf(
            Album(
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
            Album(
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
        )
    }

    fun loadManyEntries(): List<Album> {
        return loadEntries() + listOf(
            Album(
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
            Album(
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
            ),
            Album(
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
            Album(
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
            ),
            Album(
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
            Album(
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
            ),
            Album(
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
            Album(
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
            ),
            Album(
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
            Album(
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
            ),
            Album(
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
            Album(
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
        )
    }
}