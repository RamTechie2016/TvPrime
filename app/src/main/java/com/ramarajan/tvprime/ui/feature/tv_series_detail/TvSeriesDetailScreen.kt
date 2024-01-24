package  com.ramarajan.tvprime.ui.feature.tv_series_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import  com.ramarajan.tvprime.BuildConfig
import  com.ramarajan.tvprime.R
import  com.ramarajan.tvprime.data.model.cast.TvSeriesCreditsResponse
import  com.ramarajan.tvprime.data.model.details.TvSeriesDetailsResponse
import com.ramarajan.tvprime.ui.feature.common.TopBar
import  com.ramarajan.tvprime.ui.feature.tv_series_detail.components.CircularProgress
import  com.ramarajan.tvprime.ui.feature.tv_series_detail.components.ItemCastCard
import  com.ramarajan.tvprime.core.formattedYear
import java.util.*

@Composable
fun TvSeriesDetailScreen(
    navController: NavController, title: String, viewModel: TvSeriesDetailViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TopBar(title=title,navController=navController, isSearchEnabled = false)
    }) { paddingValues ->
        Box(
            modifier = Modifier.padding(
                paddingValues
            )
        ) {
            val details = viewModel.tvSeriesDetailResponse.value
            val cast = viewModel.tvSeriesCreditResponse.value
            if (details.id != null) {
                LazyColumn(content = {
                    item { ItemPoster(details) }
                    item { ItemTitle(details) }
                    item { ItemOverview(details) }
                    if(cast.id != null)
                        item { ItemCast(cast) }
                })
            }
        }
    }
}


@Composable
fun ItemPoster(response: TvSeriesDetailsResponse) {
    Box(modifier = Modifier.padding(horizontal = 15.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(BuildConfig.ORIGINAL_IMAGE_URL + response.backdropPath).crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.description),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(start = 60.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(BuildConfig.ORIGINAL_IMAGE_URL + response.posterPath).crossfade(true).build(),
            contentDescription = stringResource(id = R.string.description),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(120.dp)
                .height(160.dp)
                .align(Alignment.CenterStart)
                .clip(shape = RoundedCornerShape(10.dp))
        )
    }
}

@Composable
fun ItemTitle(response: TvSeriesDetailsResponse) {

    Spacer(modifier = Modifier.height(20.dp))

    val title = response.name ?: ""
    val year = formattedYear(response.releaseDate) ?: ""
    Text(
        text = buildAnnotatedString {
            append(title); append(" ");withStyle(style = SpanStyle(color = Color.Gray)) {
            append(
                "($year)"
            )
        }
        },
        style = MaterialTheme.typography.headlineMedium,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "R",
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            modifier = Modifier.padding(end = 10.dp)
        )

        val originalLanguage = if (response.originalLanguage != null) {
            " (${response.originalLanguage.uppercase(Locale.ROOT)})"
        } else ""
        Text(
            text = response.releaseDate + originalLanguage,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            modifier = Modifier.padding(end = 10.dp)
        )
    }


    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        content = {
            response.genres?.forEach {
                item {
                    Text(
                        text = if (it == response.genres.last()) it?.name.toString() else it?.name + ", ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }
            }
        })

    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularProgress((response.voteAverage?.toFloat()?.div(10)) ?: 0f)

        Text(
            text = "User Score",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 10.dp, end = 15.dp)
        )
    }


}

@Composable
fun ItemOverview(response: TvSeriesDetailsResponse) {
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Overview",
        style = MaterialTheme.typography.headlineSmall,
        maxLines = 1,
        modifier = Modifier.padding(start = 15.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    val lineHeight = MaterialTheme.typography.headlineMedium.fontSize * 4 / 3
    Text(
        text = response.overview ?: "",
        style = MaterialTheme.typography.bodyMedium,
        lineHeight = lineHeight,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
}

@Composable
fun ItemCast(credits: TvSeriesCreditsResponse) {
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Top Billed Cast",
        style = MaterialTheme.typography.headlineSmall,
        maxLines = 1,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    LazyRow(content = {
        credits.cast?.forEach {
            item {
                ItemCastCard(it)
            }
        }
    })
    Spacer(modifier = Modifier.height(15.dp))
}


