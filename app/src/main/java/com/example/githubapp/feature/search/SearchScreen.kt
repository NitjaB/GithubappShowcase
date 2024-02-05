package com.example.githubapp.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.feature.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    innerPadding: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel(),
) {

    Box(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Text("Search", modifier = Modifier.align(Alignment.Center))
    }
}