package com.example.knockoffspotify.ui.top_albums

import DefaultErrorScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.knockoffspotify.model.Album
import com.example.knockoffspotify.ui.components.LoadingItem
import com.example.knockoffspotify.ui.components.albums.AlbumCardCollection
import com.example.knockoffspotify.ui.components.topbar.HomeAppBar
import com.example.knockoffspotify.utils.ViewState

@Composable
fun TopAlbumsScreen(
    topAlbumsViewModel: TopAlbumsViewModel = hiltViewModel(),
    onAlbumCardClicked: (String) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            topAlbumsViewModel.getAlbums()
        }
    }
    val result: ViewState<List<Album>> = topAlbumsViewModel.state.collectAsStateWithLifecycle().value
    var isList by rememberSaveable { mutableStateOf(true) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            HomeAppBar(
                isList = isList,
                onLayoutChangeRequested = { isList = !isList },
                onSearchQueryChanged = { searchQuery = it }
            )
        },
        content = { padding ->
            TopAlbumsContent(
                result = result,
                isList = isList,
                searchQuery = searchQuery,
                onAlbumCardClicked = onAlbumCardClicked,
                padding = padding
            )
        }
    )
}

@Composable
fun TopAlbumsContent(
    result: ViewState<List<Album>>,
    isList: Boolean,
    searchQuery: String,
    onAlbumCardClicked: (String) -> Unit,
    padding: PaddingValues
) {
    Surface(
        modifier = Modifier
            .padding(padding)
            .testTag("TopAlbumsSurface"),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (result) {
            ViewState.Error -> DefaultErrorScreen(errorMessage = "Failed to load albums")
            ViewState.Loading -> LoadingItem()
            is ViewState.Success -> {
                val filteredAlbums = result.entries.filter { album ->
                    album.name.label.contains(searchQuery, ignoreCase = true)
                }
                AlbumCardCollection(albums = filteredAlbums, isList = isList, onAlbumCardClicked)
            }
        }
    }
}

