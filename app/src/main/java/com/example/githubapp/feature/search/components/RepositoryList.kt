package com.example.githubapp.feature.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.githubapp.R
import com.example.githubapp.core.components.SimpleLazyColumn
import com.example.githubapp.domain.search.models.Repository

@Composable
fun RepositoryList(
    repositories: LazyPagingItems<Repository>,
    modifier: Modifier = Modifier,
) {
    SimpleLazyColumn(
        items = repositories,
        key = { id },
        uiItemBuilder = { repository -> RepositoryItem(repository) },
        noItemsItem = { NoItems() },
        modifier = modifier,
        itemSpacing = 2.dp
    )
}

@Composable
fun NoItems(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 64.dp)
    ) {
        Text(
            text = stringResource(R.string.search_screen_no_repositories_matching_your_query),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}
