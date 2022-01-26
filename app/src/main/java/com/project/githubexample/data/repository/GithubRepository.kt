package com.project.githubexample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.githubexample.data.datasource.local.GithubLocalSource
import com.project.githubexample.data.datasource.remote.GithubRemoteSource
import com.project.githubexample.data.datasource.remote.api.GithubApiService
import com.project.githubexample.data.dto.Items
import com.project.githubexample.data.dto.User
import com.project.githubexample.data.paging.UserPagingSource
import com.project.githubexample.network.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubApiService: GithubApiService,
    private val githubRemoteSource: GithubRemoteSource,
    private val githubLocalSource: GithubLocalSource
){
    suspend fun searchUsers(id: String, page: Int)
    : NetworkState<User> =
        githubRemoteSource.searchUsers(id, page)

    fun getUsers(id: String): Flow<PagingData<Items>> = Pager(
        config = PagingConfig(1,enablePlaceholders = false)) {
            UserPagingSource(id, githubApiService)
        }.flow
}