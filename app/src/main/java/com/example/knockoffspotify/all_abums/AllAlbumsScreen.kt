package com.example.knockoffspotify.all_abums

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.knockoffspotify.model.Entry
import com.example.knockoffspotify.utils.ViewState

@Composable
fun AllAlbumsScreen(allAlbumsViewModel: AllAlbumsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        allAlbumsViewModel.getAlbums()
    }
    val result: ViewState = allAlbumsViewModel.state.collectAsState().value

    Scaffold(
        topBar = {},
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
                color = MaterialTheme.colorScheme.background,
            ) {
                when (result){
                    ViewState.Error -> Text(text = "error")
                    ViewState.Loading -> Text(text = "loading")
                    is ViewState.Success -> AlbumList(result.entries)
                }
            }
        }
    )
}

@Composable
private fun AlbumList(albums: List<Entry>) {
    LazyColumn {
        items(albums) { album ->
            AlbumCard(album)
        }
    }
}

