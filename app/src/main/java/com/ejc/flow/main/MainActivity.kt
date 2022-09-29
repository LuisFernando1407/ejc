package com.ejc.flow.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.ejc.flow.main.data.Movie
import com.ejc.ui.theme.EjcTheme
import com.ejc.util.AppError
import com.ejc.util.EMPTY
import com.ejc.util.Progress
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import com.ejc.R as AppR


@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    private fun mainModules() =:: MainModule.invoke().all()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(mainModules())

        setContent {
            EjcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    MainView(viewModel = getViewModel())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(mainModules())
    }
}

@ExperimentalCoilApi
@Composable
fun MainView(viewModel: MainViewModel) {
    when(val state = viewModel.uiState.collectAsState().value) {
        is MainUiState.Loading -> {
            Progress()
        }
        is MainUiState.Success -> {
            MovieList(movieList = state.movies)
        }
        is MainUiState.Error -> {
            AppError(error = state.exception.message)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MovieList(movieList: List<Movie>?) {
    movieList?.let { movies ->
        LazyColumn {
            itemsIndexed(items = movies) { _, item ->
                MovieItem(movie = item)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = movie.imageUrl,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(AppR.drawable.ic_launcher_background)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = movie.description,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = movie.description,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }

}

@ExperimentalCoilApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val items = arrayListOf<Movie>()

    repeat(10) {
        items.add(
            Movie(
                category = "Category",
                imageUrl = String.EMPTY,
                name = "Name",
                description = "Description"
            )
        )
    }

    EjcTheme {
        MovieList(movieList = items)
    }
}