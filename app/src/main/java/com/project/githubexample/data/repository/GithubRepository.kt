package com.project.githubexample.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.project.githubexample.data.datasource.local.GithubLocalSource
import com.project.githubexample.data.datasource.remote.GithubRemoteSource
import com.project.githubexample.data.datasource.remote.api.GithubApiService
import com.project.githubexample.data.dto.*
import com.project.githubexample.data.paging.UserInfoPagingSource
import com.project.githubexample.data.paging.SearchPagingSource
import com.project.githubexample.network.NetworkState
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubApiService: GithubApiService,
    private val githubRemoteSource: GithubRemoteSource,
    private val githubLocalSource: GithubLocalSource
){
    suspend fun searchUsers(id: String, page: Int)
    : NetworkState<SearchUserModel> =
        githubRemoteSource.searchUsers(id, page)

    fun searchUsers(id: String): LiveData<PagingData<Items>> = Pager(
        config = PagingConfig(1,enablePlaceholders = false)) {
        SearchPagingSource(id, githubApiService)
    }.liveData

    suspend fun getUserInfo(id: String)
    : NetworkState<BaseItemModel> =
        githubRemoteSource.getUserInfo(id)

    suspend fun getUserRepos(id: String)
            : NetworkState<List<BaseItemModel>> =
        githubRemoteSource.getUserRepos(id)

    fun getUserEvents(id: String): LiveData<PagingData<BaseItemModel>> = Pager(
        config = PagingConfig(1, enablePlaceholders = false)) {
        UserInfoPagingSource(id, githubApiService)
    }.liveData
}