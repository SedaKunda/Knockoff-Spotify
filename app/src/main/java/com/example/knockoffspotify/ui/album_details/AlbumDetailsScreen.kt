package com.example.knockoffspotify.ui.album_details

import DefaultErrorScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.knockoffspotify.model.Album
import com.example.knockoffspotify.ui.components.LoadingItem
import com.example.knockoffspotify.ui.components.topbar.DefaultAppBar
import com.example.knockoffspotify.utils.ViewState

@Composable
fun AlbumDetailsScreen(
    albumId: String,
    albumDetailsViewModel: AlbumDetailsViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            albumDetailsViewModel.getAlbumDetails(albumId)
        }
    }
    val result: ViewState<Album> = albumDetailsViewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            DefaultAppBar()
        },
        content = { padding ->
            AlbumDetailsContent(
                result = result,
                padding = padding
            )
        }
    )
}

@Composable
fun AlbumDetailsContent(
    result: ViewState<Album>,
    padding: PaddingValues
) {
    Surface(
        modifier = Modifier.padding(padding),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (result) {
            ViewState.Error -> DefaultErrorScreen(errorMessage = "Failed to load selected album")
            ViewState.Loading -> LoadingItem()
            is ViewState.Success -> {
                Text(text = result.entries.results[0].collectionName)
            }
        }

    }
}