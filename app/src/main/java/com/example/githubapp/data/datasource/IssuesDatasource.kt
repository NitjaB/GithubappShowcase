package com.example.githubapp.data.datasource

import com.example.githubapp.data.api.IssuesApi
import com.example.githubapp.data.helpers.safeApiCall
import com.example.githubapp.data.models.PagedData
import javax.inject.Inject

class IssuesDatasource @Inject constructor(
    private val api: IssuesApi,
) {

    suspend fun getIssues(page: Int) = safeApiCall {
        val issuesAndPullRequests = api.getIssuesAndPullRequests(page)
        return@safeApiCall if (issuesAndPullRequests.isNotEmpty()) {
            PagedData(
                totalCount = 0,
                incompleteResults = true,
                items = issuesAndPullRequests.filter { responseContent -> responseContent.pullRequestLinksResponse == null }
            )
        } else {
            PagedData(
                totalCount = 0,
                incompleteResults = false,
                items = listOf()
            )
        }
    }

    suspend fun getPullRequests(page: Int) = safeApiCall {
        val issuesAndPullRequests = api.getIssuesAndPullRequests(page)
        return@safeApiCall if (issuesAndPullRequests.isNotEmpty()) {
            PagedData(
                totalCount = 0,
                incompleteResults = true,
                items = issuesAndPullRequests.filter { responseContent -> responseContent.pullRequestLinksResponse != null }
            )
        } else {
            PagedData(
                totalCount = 0,
                incompleteResults = false,
                items = listOf()
            )
        }
    }
}
