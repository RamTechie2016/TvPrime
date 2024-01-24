package  com.ramarajan.tvprime.ui.feature

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ramarajan.tvprime.ui.feature.tv_series_detail.TvSeriesDetailScreen
import  com.ramarajan.tvprime.ui.feature.search_tv_series.SearchPageScreen
import  com.ramarajan.tvprime.ui.feature.tv_series_list.TvSeriesListScreen

@ExperimentalAnimationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.TvSeriesListScreen.route) {

        composable(
            route = Screen.TvSeriesListScreen.route
        ) {
            TvSeriesListScreen(navController = navController)
        }

        composable(
            route = Screen.TvSeriesDetailScreen.route + "?seriesId={seriesId}&seriesName={seriesName}",
            arguments = listOf(
                navArgument(name = "seriesId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "seriesName") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val seriesName = it.arguments?.getString("seriesName") ?: ""
            val seriesId = it.arguments?.getString("seriesId") ?: ""
            TvSeriesDetailScreen(navController = navController, seriesName)
        }

        composable(
            route = Screen.SearchPageScreen.route
        ) {
            SearchPageScreen(navController = navController)
        }


    }
}