package com.example.knockoffspotify.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.knockoffspotify.model.Entry
import retrofit2.HttpException
import java.io.IOException

class TopAlbumsPagingSource(
    private val service: TopAlbumsApiService,
) : PagingSource<Int, Entry>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Entry> {
        return try {
            val currentPage = params.key ?: 1
            val topAlbums = service.getTopAlbums()
            LoadResult.Page(
                data = topAlbums.feed.entry,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (topAlbums.feed.entry.isEmpty()) null else currentPage + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Entry>): Int? {
        return state.anchorPosition
    }
}