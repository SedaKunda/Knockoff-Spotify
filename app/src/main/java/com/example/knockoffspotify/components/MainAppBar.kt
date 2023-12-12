package com.example.knockoffspotify.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.knockoffspotify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    needBackButton: Boolean = true,
    isList: Boolean,
    onLayoutChangeRequested: () -> Unit
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_action_home),
                contentDescription = "app logo",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurfaceVariant),
                contentScale = ContentScale.FillBounds,
            )
        },
        navigationIcon = {
            if (needBackButton) {
                IconButton({}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "go back"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                )
            }
            IconButton(onClick = {
                onLayoutChangeRequested()
            }) {
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
