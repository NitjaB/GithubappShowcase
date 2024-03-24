package com.example.githubapp.feature.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import com.example.githubapp.feature.profile.components.ProfileHeadline
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.OnShareClicked
import com.example.githubapp.feature.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    val viewState by viewModel.viewState.collectAsState()

    Surface(
        shadowElevation = 2.dp,
        modifier = Modifier.padding(paddingValues),
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            viewState.profile?.let { notNullProfile ->
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = stringResource(R.string.default_icon_content_description),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { viewModel.onEvent(OnShareClicked) }
                        .padding(12.dp)
                        .size(24.dp)
                        .align(Alignment.End),
                    tint = MaterialTheme.colorScheme.primary
                )
                ProfileHeadline(profile = notNullProfile)
            }
            when {
                viewState.isError -> {

                }

                viewState.isLoading -> {

                }
            }
        }
    }
}
