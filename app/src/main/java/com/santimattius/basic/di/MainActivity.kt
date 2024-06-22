package com.santimattius.basic.di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.basic.di.domain.Movie
import com.santimattius.basic.di.ui.component.AppBar
import com.santimattius.basic.di.ui.component.AppBarIcon
import com.santimattius.basic.di.ui.component.AppBarIconModel
import com.santimattius.basic.di.ui.component.BasicSkeletonContainer
import com.santimattius.basic.di.ui.component.NetworkImage
import com.santimattius.basic.skeleton.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicSkeletonContainer {
                MainRoute()
            }
        }
    }
}

@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MainScreen(
        state = state,
        onRefresh = viewModel::refresh,
    )
}

@Composable
fun MainScreen(
    state: MainUiState,
    onRefresh: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.app_name),
                actions = {
                    AppBarIcon(
                        AppBarIconModel(
                            icon = Icons.Default.Refresh,
                            contentDescription = stringResource(id = R.string.refresh),
                            action = onRefresh
                        )
                    )
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            } else {
                Movies(movies = state.data)
            }
        }
    }
}

@Composable
@Suppress("MagicNumber",)
private fun Movies(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit = {},
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(2.dp),
        modifier = modifier
    ) {

        items(movies, key = { it.id }) { movie ->
            MovieItem(
                movie = movie,
                onClick = onClick,
            )
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: (Movie) -> Unit = {},
) {
    Card(
        modifier = modifier
            .padding(2.dp)
            .clickable { onClick(movie) }) {
        Box(contentAlignment = Alignment.BottomEnd) {
            NetworkImage(
                imageUrl = movie.poster,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(ratio = 0.67f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicSkeletonContainer {
        MainScreen(
            state = MainUiState(isLoading = false),
            onRefresh = {},
        )
    }
}