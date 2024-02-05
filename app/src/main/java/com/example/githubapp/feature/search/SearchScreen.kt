package com.example.githubapp.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

    Box(
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
    }
}