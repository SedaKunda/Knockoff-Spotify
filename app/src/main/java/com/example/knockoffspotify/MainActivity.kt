package com.example.knockoffspotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.knockoffspotify.ui.album_details.AlbumDetailsScreen
import com.example.knockoffspotify.ui.components.topbar.HomeAppBar
import com.example.knockoffspotify.ui.theme.KnockoffSpotifyTheme
import com.example.knockoffspotify.ui.top_albums.TopAlbumsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KnockoffSpotifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppUI()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KnockoffSpotifyTheme {
        Scaffold(
            topBar = {
                HomeAppBar(isList = true, onLayoutChangeRequested = { }, onSearchQueryChanged = { })
            },
            content = { padding ->
                Surface(
                    modifier = Modifier.padding(padding),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    AppUI()
                }
            }
        )
    }
}

@Composable
fun AppUI() {
    val navController = rememberNavController()
    SetupNavigation(navController = navController)
}

@Composable
private fun SetupNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "top_albums",
    ) {
        composable(
            route = "top_albums"
        ) {
            TopAlbumsScreen(
                onAlbumCardClicked = { albumId ->
                    navController.navigate("album_details" + "/${albumId}")
                }
            )
        }
        composable(
            route = "album_details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        )
        { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id") ?: return@composable
            AlbumDetailsScreen(albumId = id, onBackPressed = { navController.popBackStack() })
        }
    }
}