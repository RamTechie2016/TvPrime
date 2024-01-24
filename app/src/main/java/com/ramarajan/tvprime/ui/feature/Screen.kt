package  com.ramarajan.tvprime.ui.feature

sealed class Screen(val route:String){
    data object TvSeriesListScreen: Screen("tv_series_list_screen")
    data object TvSeriesDetailScreen: Screen("tv_series_details_screen")
    data object SearchPageScreen: Screen("search_page_screen")
 }
