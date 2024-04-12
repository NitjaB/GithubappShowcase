package com.example.githubapp.domain.repository.mappers

import com.example.githubapp.data.models.RepositoryResponse
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.models.Image
import com.example.githubapp.domain.search.models.Repository
import javax.inject.Inject

class DetailsMapper @Inject constructor(){
    fun map(response: RepositoryResponse) = Repository(
        id = response.id,
        name = response.name,
        url = response.url,
        author = AuthorInfo(
            response.owner.username,
            Image.RemoteImage(response.owner.avatarUrl)
        ),
        fullName = response.fullName,
        description = response.description,
        language = response.language,
        staredTimes = response.stargazersCount,
        forkNumber = response.forksCount

    )
}