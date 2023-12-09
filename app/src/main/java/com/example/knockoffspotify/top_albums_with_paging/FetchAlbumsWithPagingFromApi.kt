package com.example.knockoffspotify.top_albums_with_paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.knockoffspotify.data.remote.TopAlbumsApiService
import com.example.knockoffspotify.data.remote.TopAlbumsPagingSource
import com.example.knockoffspotify.model.Entry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAlbumsWithPagingFromApi @Inject constructor(private val service: TopAlbumsApiService) {
    fun fetchAlbums() : Flow<PagingData<Entry>> {
        return Pager(
            config = PagingConfig(pageSize = 100, prefetchDistance = 2),
            pagingSourceFactory = {
                TopAlbumsPagingSource(service)
            }
        ).flow
    }
}