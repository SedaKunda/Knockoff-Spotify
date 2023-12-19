package com.example.knockoffspotify.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.knockoffspotify.model.AlbumEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [AlbumEntity::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    private class AlbumDatabaseCallback (
        private val scope: CoroutineScope,
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.albumDao())
                }
            }
        }

        suspend fun populateDatabase(albumDao: AlbumDao) {
            val albums = Datasource().loadDbEntries()
            albumDao.insertAll(albums)
        }
    }
    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: AlbumDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): AlbumDatabase {
            // if the INSTANCE is not null, then return it; if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumDatabase::class.java,
                    "album_database"
                )
                    .addCallback(AlbumDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}