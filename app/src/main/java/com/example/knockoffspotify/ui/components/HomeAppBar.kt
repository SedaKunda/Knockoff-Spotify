package com.example.knockoffspotify.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    isList: Boolean,
    onLayoutChangeRequested: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("Top 100 Albums") },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                )
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
