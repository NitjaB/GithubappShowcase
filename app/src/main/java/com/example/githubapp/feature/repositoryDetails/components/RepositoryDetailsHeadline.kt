package com.example.githubapp.feature.repositoryDetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.core.components.Image
import com.example.githubapp.designSystem.theme.gray500
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.search.models.Repository
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent.OnStarButtonClicked

@Composable
fun RepositoryDetailsHeadline(
    repository: Repository,
    isStarred: Boolean = false,
    isStaredLoading: Boolean = false,
    interactionHandler: (OnStarButtonClicked) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                image = repository.author.avatar,
                contentDescription = stringResource(R.string.default_image_content_description),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp)
            )
            Text(
                text = repository.author.username,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = gray500,
                modifier = Modifier.padding(
                    start = 4.dp,
                    end = 80.dp,
                )
            )
        }
        Row {
            Text(
                text = repository.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(end = 24.dp, top = 8.dp),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Row {
            if (!repository.description.isNullOrEmpty()) {
                Text(
                    text = repository.description,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(color = gray500)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_url
                ),
                contentDescription = stringResource(id = R.string.default_image_content_description),
                tint = gray500
            )
            Text(
                text = repository.url ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(end = 24.dp, start = 4.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = stringResource(R.string.default_icon_content_description),
                tint = gray500,
            )
            Text(
                text = repository.staredTimes.toString(),
                Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(id = R.string.repository_details_screen_star),
                color = gray500
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_fork),
                contentDescription = stringResource(R.string.default_icon_content_description),
                tint = gray500,
                modifier = Modifier.padding(end = 4.dp, start = 8.dp)
            )
            Text(text = repository.forkNumber.toString())
            Text(
                text = stringResource(id = R.string.repository_details_screen_fork),
                Modifier.padding(horizontal = 4.dp),
                color = gray500
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            onClick = {
                interactionHandler(
                    OnStarButtonClicked(
                        repository.author.username,
                        repository.name,
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(4.dp)
        ) {
            if (!isStaredLoading) {
                Icon(
                    painter = painterResource(
                        if (isStarred) {
                            R.drawable.ic_star_filled
                        } else {
                            R.drawable.ic_star
                        }
                    ),
                    tint = gray500,
                    modifier = Modifier.padding(end = 6.dp),
                    contentDescription = stringResource(id = R.string.default_icon_content_description),
                )
            } else {
                CircularProgressIndicator(
                    color = gray500,
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(16.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.repository_details_screen_star_button),
                color = gray500
            )
        }
    }
}

@Preview
@Composable
private fun RepositoryDetailsHeadlinePreview() {
    RepositoryDetailsHeadline(
        Repository(
            id = 1,
            name = "RepoName",
            author = AuthorInfo(
                username = "Karlo",
                avatar = com.example.githubapp.domain.models.Image.LocalImage(R.drawable.ic_launcher_foreground),
            ),
            fullName = "Karlo/Kotlin",
            description = "Description",
            language = "",
            staredTimes = 1900,
            forkNumber = 150,
            url = "www.github.com"
        ),
        interactionHandler = {},
    )
}

@Preview
@Composable
private fun RepositoryDetailsHeadlineLongPreview() {
    RepositoryDetailsHeadline(
        Repository(
            id = 1,
            name = List(10) { "LongRepoName" }.joinToString(""),
            author = AuthorInfo(
                username = List(10) { "LongUsername" }.joinToString(""),
                avatar = com.example.githubapp.domain.models.Image.LocalImage(R.drawable.ic_launcher_foreground),
            ),
            fullName = "Karlo/Kotlin",
            description = List(30) { "LongDescription" }.joinToString(""),
            language = "Kotlin",
            staredTimes = 1900,
            forkNumber = 150,
            url = "cghdsjgcdhyugdyhukagDHSKUGYFDUSGYDGSYAKHJDIGHUfgfdghgfhdghfgdhfgdhj"
        ),
        interactionHandler = {},
    )
}

@Preview
@Composable
private fun RepositoryDetailsLoadingStarredHeadlineLongPreview() {
    RepositoryDetailsHeadline(
        Repository(
            id = 1,
            name = List(10) { "LongRepoName" }.joinToString(""),
            author = AuthorInfo(
                username = List(10) { "LongUsername" }.joinToString(""),
                avatar = com.example.githubapp.domain.models.Image.LocalImage(R.drawable.ic_launcher_foreground),
            ),
            fullName = "Karlo/Kotlin",
            description = List(30) { "LongDescription" }.joinToString(""),
            language = "Kotlin",
            staredTimes = 1900,
            forkNumber = 150,
            url = "cghdsjgcdhyugdyhukagDHSKUGYFDUSGYDGSYAKHJDIGHUfgfdghgfhdghfgdhfgdhj"
        ),
        isStaredLoading = true,
        interactionHandler = {},
    )
}