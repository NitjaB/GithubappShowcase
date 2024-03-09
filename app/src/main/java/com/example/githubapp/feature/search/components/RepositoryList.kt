package com.example.githubapp.feature.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.githubapp.R
import com.example.githubapp.designSystem.theme.GithubAppTheme
import com.example.githubapp.domain.search.models.Repository

@Composable
fun RepositoryList(
    repositories: LazyPagingItems<Repository>,
    modifier: Modifier = Modifier,
) {
    if (repositories.loadState.append.endOfPaginationReached && repositories.itemCount == 0) {
        NoItems(modifier)
    } else {
        LazyColumn(modifier) {
            items(
                repositories.itemCount,
                key = repositories.itemKey { repository -> repository.id }
            ) { item ->
                RepositoryItem(repository = repositories[item]!!)
            }
            when {
                repositories.loadState.refresh == LoadState.Loading -> item {
                    LoadingFirstPage(Modifier.fillParentMaxSize())
                }

                repositories.loadState.append == LoadState.Loading -> item {
                    LoadingNextPageIndicator()
                }


                repositories.loadState.refresh is LoadState.Error -> item {
                    ErrorWhileLoadingFirstPage(
                        { repositories.retry() },
                        Modifier.fillParentMaxSize()
                    )
                }

                repositories.loadState.append is LoadState.Error -> item {
                    ErrorWhileLoadingMorePages({ repositories.retry() })
                }
            }
        }
    }
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

@Composable
fun LoadingFirstPage(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
fun LoadingNextPageIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
fun ErrorWhileLoadingFirstPage(
    onRetryClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error_indicator),
            contentDescription = stringResource(R.string.default_icon_content_description),
            modifier = Modifier.size(80.dp),
        )
        Text(
            text = stringResource(R.string.error),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp),
        )
        Text(
            text = stringResource(R.string.unexpected_error_description),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp),
        )
        OutlinedButton(
            onClick = { onRetryClicked() },
            modifier = Modifier.padding(top = 8.dp),
        ) {
            Text(
                text = stringResource(R.string.try_again),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
fun ErrorWhileLoadingMorePages(
    onRetryClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { onRetryClicked() },
            modifier = Modifier
                .padding(top = 8.dp, bottom = 24.dp)
                .align(Alignment.Center),
        ) {
            Text(
                text = stringResource(R.string.try_again),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Preview
@Composable
private fun NoItemsPreview() {
    GithubAppTheme {
        NoItems()
    }
}

@Preview
@Composable
private fun LoadingFirstPagePreview() {
    GithubAppTheme {
        LoadingFirstPage()
    }
}

@Preview
@Composable
private fun LoadingNextPagePreview() {
    GithubAppTheme {
        LoadingNextPageIndicator()
    }
}

@Preview
@Composable
private fun ErrorWhileLoadingFirstPagePreview() {
    GithubAppTheme {
        ErrorWhileLoadingFirstPage({ })
    }
}

@Preview
@Composable
private fun ErrorWhileLoadingNextPagePreview() {
    GithubAppTheme {
        ErrorWhileLoadingMorePages({})
    }
}