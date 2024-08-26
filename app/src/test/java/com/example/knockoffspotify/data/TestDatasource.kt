package com.example.knockoffspotify.data

import com.example.knockoffspotify.data.local.Datasource
import com.example.knockoffspotify.model.Album
import com.example.knockoffspotify.model.Feed
import com.example.knockoffspotify.model.Id2

class TestDatasource {
    private fun getEntries(): List<Album> {
        return Datasource().loadEntries()
    }

    fun getFeed(): Feed {
        return Feed(
            album = getEntries(),
            id = Id2(""),
        )
    }
}