package com.example.knockoffspotify.data

import com.example.knockoffspotify.data.local.Datasource
import com.example.knockoffspotify.data.model.Feed
import com.example.knockoffspotify.data.model.Id2
import com.example.knockoffspotify.data.model.TopAlbum

class TestDatasource {
    private fun getEntries(): List<TopAlbum> {
        return Datasource().loadEntries()
    }

    fun getFeed(): Feed {
        return Feed(
            topAlbum = getEntries(),
            id = Id2(""),
        )
    }
}