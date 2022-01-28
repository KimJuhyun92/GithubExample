package com.project.githubexample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.githubexample.data.datasource.remote.api.GithubApiService
import com.project.githubexample.data.dto.BaseItemModel
import retrofit2.HttpException
import java.io.IOException

class UserInfoPagingSource(private val id: String, private val githubApiService: GithubApiService) :
    PagingSource<Int, BaseItemModel>() {

    private companion object {
        const val DEFAULT_PAGE_INDEX = -1

        const val USER_PAGE_INDEX = -1
        const val REPO_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseItemModel> {
        val page = params.key ?: DEFAULT_PAGE_INDEX

        val data = when(page) {
            USER_PAGE_INDEX -> {
                listOf(githubApiService.getUser(id))
            }
            REPO_PAGE_INDEX -> {
                githubApiService.getUserRepos(id)
            }
            else -> {
                githubApiService.getUserEvent(id, page)
            }
        }

        return try {
            LoadResult.Page(
                data = data,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (data.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            val error = IOException(exception)
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BaseItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}