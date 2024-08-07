package com.example.knockoffspotify.ui.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.knockoffspotify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    isList: Boolean,
    onLayoutChangeRequested: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var searchMode by rememberSaveable { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (searchMode) {
                TextField(
                    value = searchQuery, onValueChange = {
                        onSearchQueryChanged(it)
                        searchQuery = it
                    },
                    placeholder = { Text(stringResource(R.string.search)) },
                    modifier = Modifier.testTag("SearchTextField"),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
                    )

                )
            }
            else {
                Text(stringResource(R.string.top_100_albums))
            }
        },
        actions = {
            IconButton(onClick = { searchMode = !searchMode }) {
                if (searchMode) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close search",
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search",
                    )
                }
            }
            IconButton(onClick = {
                onLayoutChangeRequested()
            }, modifier = modifier.testTag("ChangeViewIcon")) {
                if (!isList) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = "list view",
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.GridView,
                        contentDescription = "grid view",
                    )
                }
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "more options",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
    )
}
