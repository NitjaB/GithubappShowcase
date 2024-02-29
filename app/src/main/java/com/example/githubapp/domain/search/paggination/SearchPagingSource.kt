package com.example.githubapp.domain.search.paggination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubapp.domain.models.PagedData
import com.example.githubapp.domain.search.models.Repository

class SearchPagingSource(
    private val source: suspend (page: Int) -> Result<PagedData<Repository>>,
) : PagingSource<Int, Repository>() {

    private companion object {
        const val FIRST_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val page = params.key ?: FIRST_PAGE
        val result = source(page)
        return result.fold(
            onSuccess = { response ->
                LoadResult.Page(
                    data = response.items,
                    prevKey = if (page == FIRST_PAGE) null else page.minus(1),
                    nextKey = if (response.isLastPage) null else page.plus(1),
                )
            },
            onFailure = { throwable ->
                LoadResult.Error(throwable)
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? = null
}
