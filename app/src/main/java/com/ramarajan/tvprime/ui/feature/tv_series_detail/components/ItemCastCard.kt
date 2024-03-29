package  com.ramarajan.tvprime.ui.feature.tv_series_detail.components

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import  com.ramarajan.tvprime.BuildConfig
import  com.ramarajan.tvprime.R
import  com.ramarajan.tvprime.data.model.cast.CastItem


@Composable
fun ItemCastCard(castItem: CastItem?) {
    Card(
        modifier = Modifier
            .padding(start = 15.dp)
            .background(color = Color.White)
            .clickable {

            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.width(120.dp)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.LARGE_IMAGE_URL + castItem?.profilePath)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.description),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
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
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = castItem?.name ?: "",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp),
            )
            Text(
                text = castItem?.character ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp),
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}