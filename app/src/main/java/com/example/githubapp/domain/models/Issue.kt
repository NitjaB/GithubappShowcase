package com.example.githubapp.domain.models

import com.example.githubapp.domain.search.models.Repository

data class Issue(
    val id: Int,
    val repoName: String,
    val title: String,
    val authorInfo: AuthorInfo,
    val description: String,
    val status: State,
    val repository: Repository,
) {
    enum class State {
        OPEN,
        CLOSED,
    }
}
