package com.example.knockoffspotify.top_albums_with_paging

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.knockoffspotify.components.AlbumCardList
import com.example.knockoffspotify.components.HomeAppBar
import com.example.knockoffspotify.components.LoadingItem
import com.example.knockoffspotify.model.Entry

@Composable
fun TopAlbumsWithPagingScreen(
    topAlbumsViewModel: TopAlbumsWithPagingViewModel = hiltViewModel(),
    onAlbumCardClicked: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        topAlbumsViewModel.getAlbums()
    }
    val result: LazyPagingItems<Entry> = topAlbumsViewModel.state.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeAppBar(
                needBackButton = false,
                isList = true,
                onLayoutChangeRequested = {})
        },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
                color = MaterialTheme.colorScheme.background,
            ) {
                result.apply {
                    AlbumList(this, onAlbumCardClicked)
                    when {
                        loadState.refresh is LoadState.Loading -> LoadingItem()

                        loadState.refresh is LoadState.Error -> Text(text = "error")

                        loadState.append is LoadState.Loading -> LoadingItem()

                        loadState.append is LoadState.Error -> Text(text = "error")
                    }
                }
            }
        }
    )

}

@Composable
private fun AlbumList(albums: LazyPagingItems<Entry>, onAlbumCardClicked: (String) -> Unit) {
    LazyColumn {
        items(albums.itemCount) { index ->
            AlbumCardList(albums[index]!!, onAlbumCardClicked)
        }
    }
}