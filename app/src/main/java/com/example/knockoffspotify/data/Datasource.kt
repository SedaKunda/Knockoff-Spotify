package com.example.knockoffspotify.data

import com.example.knockoffspotify.model.*

class Datasource() {
    fun loadEntries(): List<Entry> {
        return listOf(
            Entry(
                imName = ImName("1989 (Taylor's Version) [Deluxe]"),
                imArtist = ImArtist("artist1", Attributes7("")),
                imReleaseDate = ImReleaseDate("2023-10-20T00:00:00-07:00", Attributes9("")),
                imImage = listOf(
                    ImImage(
                        label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/55x55bb.png",
                        attributes = Attributes(height = "55")
                    ),
                    ImImage(
                        label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/60x60bb.png",
                        attributes = Attributes(height = "60")
                    ),
                    ImImage(
                        label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/170x170bb.png",
                        attributes = Attributes(height = "170")
                    )
                ),
                imItemCount = ImItemCount("22"),
                imPrice = ImPrice("$13.99", Attributes2("13.99", "USD")),
                imContentType = ImContentType(
                    ImContentType2(Attributes3("Album", "Album")),
                    Attributes4("Music", "Music")
                ),
                rights = Rights("℗ 2023 Taylor Swift"),
                title = Title("1989 (Taylor's Version) [Deluxe] - Taylor Swift"),
                link = Link(
                    Attributes5(
                        "alternate",
                        "text/html",
                        "https://music.apple.com/us/album/1989-taylors-version-deluxe/1713845538?uo=2"
                    )
                ),
                id = Id(
                    "https://music.apple.com/us/album/1989-taylors-version-deluxe/1713845538?uo=2",
                    Attributes6("1713845538")
                ),
                category = Category(Attributes8("", "", "", "")),
            )
        )
    }
}