package com.example.githubapp.domain.repository
import com.example.githubapp.data.details.DetailsDatasource
import com.example.githubapp.domain.details.mappers.DetailsMapper
import com.example.githubapp.domain.helpers.toResult
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(
    private val datasource: DetailsDatasource,
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
}