package com.example.githubapp.domain.repository.usecase

import com.example.githubapp.domain.repository.RepositoriesRepository
import javax.inject.Inject

class GetRepository @Inject constructor(
    private val repository: RepositoriesRepository
) {

    suspend operator fun invoke(
        owner: String,
        repoName: String,
    ) = repository.getRepositoryDetails(owner, repoName)

    suspend fun isStarred(
        owner: String,
        repoName: String,
    ) = repository.isStared(owner, repoName)
}