package com.example.githubapp.feature.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.core.components.GithubAppLabel
import com.example.githubapp.designSystem.theme.Typography
import com.example.githubapp.designSystem.theme.green
import com.example.githubapp.designSystem.theme.lightBlue

@Composable
fun OverviewScreen(
    paddingValues: PaddingValues,
) {
    Column(
        Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.overview_screen_my_work_title),
            modifier = Modifier.padding(bottom = 16.dp, start = 12.dp),
            style = Typography.titleMedium,
        )
        GithubAppLabel(
            label = stringResource(R.string.issues),
            indicator = R.drawable.ic_issues,
            indicatorBackground = green,
            modifier = Modifier.clickable {}
        )
        GithubAppLabel(
            label = stringResource(R.string.pull_requests),
            indicator = R.drawable.ic_pull_requests,
            indicatorBackground = lightBlue,
            modifier = Modifier.clickable {}
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    OverviewScreen(paddingValues = PaddingValues())
}
