package com.example.githubapp.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubapp.R
import com.example.githubapp.feature.search.components.RepositoryList
import com.example.githubapp.feature.search.components.SearchBar
import com.example.githubapp.feature.search.model.SearchScreenEvent.onOpenFiltersClicked
import com.example.githubapp.feature.search.model.SearchScreenEvent.onSearchTextChanged
import com.example.githubapp.feature.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    innerPadding: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

    val repositories = state.repositories.collectAsLazyPagingItems()

    Column(
        Modifier
            .padding(innerPadding)
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchText,
            onTextChange = { text -> viewModel.onEvent(onSearchTextChanged(text)) },
            onConfigureClicked = { viewModel.onEvent(onOpenFiltersClicked) },
            modifier = Modifier.padding(bottom = 4.dp)
        )
        if (state.showRepositoryList) {
            RepositoryList(
                repositories = repositories,
                modifier = Modifier.weight(1f, true),
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.search_screen_start_search_hint),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}