package com.example.boardgamesapp.screens.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardgamesapp.ExploreGamesOverviewModel
import com.example.boardgamesapp.components.BigCardListItem

@ExperimentalMaterial3Api
@Composable
fun ExploreScreen(
    toDetailPage: () -> Unit,
    exploreGamesOverviewModel: ExploreGamesOverviewModel = viewModel()
) {
    val gamesOverviewState by exploreGamesOverviewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = gamesOverviewState.searchText,
            onQueryChange = {
                exploreGamesOverviewModel.setNewSearchText(it)
            },
            onSearch = {
                exploreGamesOverviewModel.searchForGames()
            },
            active = gamesOverviewState.searchActive,
            onActiveChange = {
                exploreGamesOverviewModel.setActiveSearch(it)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            },
            trailingIcon = {
                if (gamesOverviewState.searchActive) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close icon",
                        modifier = Modifier.clickable {
                            if (gamesOverviewState.searchText.isNotEmpty()) {
                                exploreGamesOverviewModel.clearSearchText()
                            } else {
                                exploreGamesOverviewModel.setActiveSearch(false)
                            }
                        }
                    )
                }
            },
            placeholder = { Text("Search games") }
        ) {
            gamesOverviewState.searchHistory.forEach {
                Row(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 12.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = "History icon"
                    )
                    Text(
                        text = it,
                        modifier = Modifier.clickable {
                            exploreGamesOverviewModel.setNewSearchText(it)
                            exploreGamesOverviewModel.searchForGames()
                        }
                    )
                }
            }
        }
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(
                    text = "Trending",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )
            }
            items(gamesOverviewState.currentGamesList) {
                BigCardListItem(
                    title = it.title,
                    minPlayTime = it.minPlayTime,
                    maxPlayTime = it.maxPlayTime,
                    minPlayers = it.minPlayers,
                    maxPlayers = it.maxPlayers,
                    shortDescription = it.shortDescription,
                    thumbnail = it.thumbnail,
                    image = it.image,
                    toDetailPage = toDetailPage
                )
            }
        }
    }
}
