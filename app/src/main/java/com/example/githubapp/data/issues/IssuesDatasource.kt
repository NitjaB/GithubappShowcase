package com.example.githubapp.data.issues

import com.example.githubapp.data.helpers.safeApiCall
import javax.inject.Inject

class IssuesDatasource @Inject constructor(
    private val api: IssuesApi,
) {

    suspend fun getIssuesAndPullRequestsAssignedToUser(page: Int) = safeApiCall {
        api.getIssuesAndPullRequestsAssignedToUser(page)
    }
}
