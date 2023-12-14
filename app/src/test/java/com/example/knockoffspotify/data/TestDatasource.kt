package com.example.knockoffspotify.data

import com.example.knockoffspotify.data.local.Datasource
import com.example.knockoffspotify.model.Author
import com.example.knockoffspotify.model.Entry
import com.example.knockoffspotify.model.Feed
import com.example.knockoffspotify.model.Icon
import com.example.knockoffspotify.model.Id2
import com.example.knockoffspotify.model.Name
import com.example.knockoffspotify.model.Rights2
import com.example.knockoffspotify.model.Title2
import com.example.knockoffspotify.model.Updated
import com.example.knockoffspotify.model.Uri

class TestDatasource {
    private fun getEntries(): List<Entry> {
        return Datasource().loadEntries()
    }

    fun getFeed(): Feed {
        return Feed(
            entry = getEntries(),
            author = Author(Name(""), Uri("")),
            id = Id2(""),
            icon = Icon(""),
            link = listOf(),
            title = Title2(""),
            rights = Rights2(""),
            updated = Updated(""),
        )
    }
}