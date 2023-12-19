package com.example.knockoffspotify.data.local

import androidx.room.Room
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.knockoffspotify.MainActivity
import com.example.knockoffspotify.data.TestDatasource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AlbumDatabaseTest {
    private lateinit var albumDao: AlbumDao
    private lateinit var db: AlbumDatabase

    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        albumDao = db.albumDao()
    }

    @After
    @Throws(IOException::class)
    fun deleteDb() {
        db.close()
    }

    @Test
    fun canInsertAndGetById() = runBlocking {
        //given
        val albums = TestDatasource().getDbEntries()

        albumDao.insertAll(albums)

        //when
        val oneItem = albumDao.getById("1713845538")

        // then
        assertEquals(oneItem.artist, "Taylor Swift")
    }

    @Test
    fun canGetAllTopAlbums() = runBlocking {
        val albums = TestDatasource().getDbEntries()
        albumDao.insertAll(albums)

        val allItems = albumDao.getAllTopAlbums()
        //todo
    }
}