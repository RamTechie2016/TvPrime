package  com.ramarajan.tvprime.ui.feature.search_tv_series.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import  com.ramarajan.tvprime.BuildConfig
import  com.ramarajan.tvprime.R
import  com.ramarajan.tvprime.data.model.tvseries.TvSeriesItem
import  com.ramarajan.tvprime.ui.feature.Screen

@Composable
fun SearchTvSeriesItemCard(item: TvSeriesItem?, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(color = Color.White)
            .clickable {
                navController.navigate(Screen.TvSeriesDetailScreen.route + "?seriesId=${item?.id.toString()}&seriesName=${item?.originalTitle}")
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Row {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.ORIGINAL_IMAGE_URL + item?.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.description),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(150.dp)
                    .height(160.dp),
                loading = {
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_img_loading))
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            )
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Spacer(modifier = Modifier.height(10.dp))
                val lineHeight = MaterialTheme.typography.headlineLarge.fontSize * 4 / 3
                val lineHeight2 = MaterialTheme.typography.labelMedium.fontSize * 4 / 3
                Text(
                    text = item?.originalTitle ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = lineHeight
                )
                Text(
                    text = item?.firstAirAt ?: "",
                    style = MaterialTheme.typography.labelMedium,
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = item?.overview ?: "",
                    style = MaterialTheme.typography.labelMedium,
                    lineHeight = lineHeight2,
                    maxLines = 5,
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}