package com.ramarajan.tvprime.ui.feature.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ramarajan.tvprime.ui.feature.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "Tv prime",
    navController: NavController,
    isSearchEnabled: Boolean? = false
) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            isSearchEnabled.let {
                when {
                    it == true -> {
                        IconButton(onClick = { navController.navigate(Screen.SearchPageScreen.route) }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                }
            }

        }
    )
}
