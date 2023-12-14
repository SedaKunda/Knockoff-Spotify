package com.example.knockoffspotify.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.knockoffspotify.model.Album
import retrofit2.HttpException
import java.io.IOException

class TopAlbumsPagingSource(
    private val service: TopAlbumsApiService,
) : PagingSource<Int, Album>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        return try {
            val currentPage = params.key ?: 1
            val topAlbums = service.getTopAlbums()
            LoadResult.Page(
                data = topAlbums.feed.album,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (topAlbums.feed.album.isEmpty()) null else currentPage + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return state.anchorPosition
    }
}