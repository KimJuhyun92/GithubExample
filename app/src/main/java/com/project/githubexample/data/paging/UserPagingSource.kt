package com.project.githubexample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.githubexample.data.datasource.remote.api.GithubApiService
import com.project.githubexample.data.dto.Items
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource (private val id: String, private val githubApiService: GithubApiService) : PagingSource<Int, Items>(){

    private companion object {
        const val DEFAULT_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Items> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        val user = githubApiService.searchUsers(id, params.loadSize)

        return try {
            LoadResult.Page(
                data = user.items,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (user.items.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Items>): Int? {
        return null
    }

}