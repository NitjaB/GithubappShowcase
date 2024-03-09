package com.example.githubapp.feature.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.search.models.Repository

@Composable
fun RepositoryItem(
    repository: Repository,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        Image(
            imageVector = Icons.Outlined.Warning,
            contentDescription = stringResource(R.string.default_image_content_description),
            modifier = Modifier.size(60.dp)
        )
        Column(Modifier.padding(start = 8.dp)) {
            Text(text = repository.name)
            Text(text = "Here goes description text")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(R.string.default_icon_content_description)
                )
                Text(text = "100")
            }
        }
    }
}

@Preview
@Composable
fun RepositoryItemPreview() {
    RepositoryItem(
        Repository(
            id = 1,
            name = "Kotlin",
            author = AuthorInfo(
                username = "",
                avatar = com.example.githubapp.domain.models.Image.LocalImage(R.drawable.ic_launcher_foreground),
            ),
            fullName = ""
        )
    )
}
