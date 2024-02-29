package com.example.githubapp.domain.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.githubapp.data.datasource.SearchDatasource
import com.example.githubapp.domain.helpers.toResult
import com.example.githubapp.domain.models.PagedData
import com.example.githubapp.domain.search.mappers.RepositoryMapper
import com.example.githubapp.domain.search.paggination.SearchPagingSource
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchDatasource: SearchDatasource,
    private val repositoryMapper: RepositoryMapper,
) {

    private companion object {
        const val PAGE_SIZE = 20
    }

    private suspend fun getRepositories(
        query: String,
        page: Int,
    ) = searchDatasource.getRepositories(query, page).toResult { content ->
        PagedData(
            isLastPage = !content.incompleteResults,
            items = content.items.map { item -> repositoryMapper.map(item) }
        )
    }

    fun getRepositories(query: String) = Pager(
        config = PagingConfig(PAGE_SIZE),
        pagingSourceFactory = {
            SearchPagingSource { page -> getRepositories(query, page) }
        }
    ).flow
}
