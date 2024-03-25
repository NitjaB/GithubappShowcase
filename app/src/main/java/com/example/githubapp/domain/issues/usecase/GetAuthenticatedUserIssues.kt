package com.example.githubapp.domain.issues.usecase

import com.example.githubapp.domain.issues.IssuesRepository
import javax.inject.Inject

class GetAuthenticatedUserIssues @Inject constructor(
    private val repository: IssuesRepository,
) {

    operator fun invoke() = repository.getAuthenticatedUserIssues()
}
