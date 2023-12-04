package com.example.knockoffspotify

import com.example.knockoffspotify.model.Entry
import com.example.knockoffspotify.model.ImArtist
import com.example.knockoffspotify.model.ImName
import com.example.knockoffspotify.model.ImReleaseDate

class Datasource() {
    fun loadEntries(): List<Entry> {
        return listOf(
            Entry(
                imName = ImName("Name1"),
                imArtist = ImArtist("artist1"),
                imReleaseDate = ImReleaseDate("2023-10-20T00:00:00-07:00")
            ),
            Entry(
                imName = ImName("Other Name"),
                imArtist = ImArtist("artist2"),
                imReleaseDate = ImReleaseDate("2023-10-20T00:00:00-07:00")
            ),
            Entry(
                imName = ImName("Some name"),
                imArtist = ImArtist("artist3"),
                imReleaseDate = ImReleaseDate("2023-10-20T00:00:00-07:00")
            ),
            Entry(
                imName = ImName("A name"),
                imArtist = ImArtist("artist4"),
                imReleaseDate = ImReleaseDate("2023-10-20T00:00:00-07:00")
            ),
            Entry(
                imName = ImName("The name"),
                imArtist = ImArtist("artist5"),
                imReleaseDate = ImReleaseDate("2023-10-20T00:00:00-07:00")
            ),
            Entry(
                imName = ImName("Name number 6"),
                imArtist = ImArtist("artist6"),
                imReleaseDate = ImReleaseDate("2023-10-20T00:00:00-07:00")
            ),
            Entry(
                imName = ImName("Seventh Name"),
                imArtist = ImArtist("artist7"),
                imReleaseDate = ImReleaseDate("2023-10-20T00:00:00-07:00")
            ),
        )
    }
}