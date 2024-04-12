package com.example.githubapp.domain.repository
import com.example.githubapp.data.repositoryDetails.RepositoryDetailsDatasource
import com.example.githubapp.domain.repository.mappers.DetailsMapper
import com.example.githubapp.domain.helpers.toResult
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(
    private val datasource: RepositoryDetailsDatasource,
    private val mapper: DetailsMapper
) {
    suspend fun getRepositoryDetails(
        owner: String,
        repoName: String,
    ) = datasource.getRepositoryDetails(owner, repoName).toResult { content ->
        mapper.map(content)
    }

    suspend fun isStared(
        owner: String,
        repoName: String
    ): Result<Boolean> {
        return datasource.isStarred(owner, repoName).toResult()
    }

    suspend fun star(
        owner: String,
        repoName: String
    ): Result<Boolean> {
        return datasource.star(owner, repoName).toResult()
    }

    suspend fun unStar(
        owner: String,
        repoName: String
    ): Result<Boolean> {
        return  datasource.unStar(owner,repoName).toResult()
    }
}