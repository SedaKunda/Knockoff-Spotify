package com.example.knockoffspotify.top_abums

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.knockoffspotify.components.AlbumCard
import com.example.knockoffspotify.components.HomeAppBar
import com.example.knockoffspotify.components.LoadingItem
import com.example.knockoffspotify.utils.ViewState

@Composable
fun TopAlbumsScreen(
    topAlbumsViewModel: TopAlbumsViewModel = hiltViewModel(),
    onAlbumCardClicked: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        topAlbumsViewModel.getAlbums()
    }
    val result: ViewState = topAlbumsViewModel.state.collectAsState().value
    var isList by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            HomeAppBar(
                needBackButton = false,
                isList = isList,
                onLayoutChangeRequested = { isList = !isList }) // todo Avoid backwards writes
        },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
                color = MaterialTheme.colorScheme.background,
            ) {
                when (result) {
                    ViewState.Error -> Text(text = "error") //todo
                    ViewState.Loading -> LoadingItem()
                    is ViewState.Success -> {
                        AlbumCard(albums = result.entries, isList = isList, onAlbumCardClicked)
                    }
                }
            }
        }
    )
}

