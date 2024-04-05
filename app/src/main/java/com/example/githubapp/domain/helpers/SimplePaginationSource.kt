package com.example.githubapp.domain.helpers

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.*
import androidx.paging.PagingState
import com.example.githubapp.domain.models.PagedData

class SimplePaginationSource<T : Any>(
    private val source: suspend (page: Int) -> Result<PagedData<T>>,
) : PagingSource<Int, T>() {

    private companion object {
        const val FIRST_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: FIRST_PAGE
        val result = source(page)
        return if (result.isSuccess) {
            Page(
                data = result.getOrNull()?.items ?: listOf(),
                prevKey = if (page == FIRST_PAGE) null else page.minus(1),
                nextKey = if (result.getOrNull()?.isLastPage == false) page.plus(1) else null,
            )
        } else {
            Error(result.exceptionOrNull() ?: Throwable())
        }
    }
}