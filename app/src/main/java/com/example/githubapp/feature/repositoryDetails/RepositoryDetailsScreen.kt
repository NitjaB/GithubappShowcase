package com.example.githubapp.feature.repositoryDetails

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.R
import com.example.githubapp.feature.repositoryDetails.components.RepositoryDetailsHeadline
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsVMParam
import com.example.githubapp.feature.repositoryDetails.viewmodel.RepositoryDetailsViewModel
@Composable
fun DetailsScreen(
    owner: String,
    repoName: String,
) {
    val viewModel = hiltViewModel<RepositoryDetailsViewModel, RepositoryDetailsViewModel.RepositoryDetailsViewModelFactory> { factory ->
        factory.create(
            RepositoryDetailsVMParam(owner, repoName)
        )
    }
    val viewState by viewModel.viewState.collectAsState()
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = stringResource(R.string.default_icon_content_description),
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .clickable { viewModel.onEvent(RepositoryDetailsScreenEvent.NavigateBack) }
                    .padding(4.dp)
                    .size(30.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = stringResource(id = R.string.default_icon_content_description),
                modifier = Modifier
                    .padding(6.dp)
                    .clip(CircleShape)
                    .clickable { viewModel.onEvent(RepositoryDetailsScreenEvent.OnShareClicked) }
                    .padding(4.dp)
                    .size(25.dp)
            )
        }
        viewState.details?.let { notNullRepository ->
            RepositoryDetailsHeadline(
                notNullRepository,
                viewState.isStarredByUser,
            )
        }
    }
}