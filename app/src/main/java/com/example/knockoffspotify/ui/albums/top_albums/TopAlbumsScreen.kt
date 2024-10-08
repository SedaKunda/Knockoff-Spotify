package com.example.knockoffspotify.ui.albums.top_albums

import DefaultErrorScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.knockoffspotify.domain.model.TopAlbum
import com.example.knockoffspotify.ui.components.LoadingItem
import com.example.knockoffspotify.ui.components.top_albums.TopAlbumCardCollection
import com.example.knockoffspotify.ui.components.topbar.HomeAppBar
import com.example.knockoffspotify.utils.ViewState

@Composable
fun TopAlbumsScreen(
    topAlbumsViewModel: TopAlbumsViewModel = hiltViewModel(),
    onAlbumCardClicked: (String) -> Unit
) {
    val result: ViewState<List<TopAlbum>> =
        topAlbumsViewModel.state.collectAsStateWithLifecycle().value
    val searchQuery by topAlbumsViewModel.searchQuery.collectAsStateWithLifecycle()
    var isList by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            HomeAppBar(
                isList = isList,
                onLayoutChangeRequested = { isList = !isList },
                onSearchQueryChanged = topAlbumsViewModel::onSearchQueryChanged,
                searchQuery = searchQuery
            )
        },
        content = { padding ->
            TopAlbumsContent(
                result = result,
                isList = isList,
                onAlbumCardClicked = onAlbumCardClicked,
                padding = padding
            )
        }
    )
}

@Composable
fun TopAlbumsContent(
    result: ViewState<List<TopAlbum>>,
    isList: Boolean,
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
            is ViewState.Success -> TopAlbumCardCollection(
                topAlbums = result.entries,
                isList = isList,
                onAlbumCardClicked
            )
        }
    }
}
