package com.project.githubexample.data.datasource.remote

import com.project.githubexample.data.datasource.remote.api.GithubApiService
import com.project.githubexample.data.dto.BaseItemModel
import com.project.githubexample.data.dto.SearchUserModel
import com.project.githubexample.network.NetworkState
import com.project.githubexample.network.callRemoteApi

class GithubRemoteSource ( private val githubApiService: GithubApiService) {

    suspend fun searchUsers(id: String, page: Int)
    : NetworkState<SearchUserModel> =
        callRemoteApi { githubApiService.searchUsers(id, page) }

    suspend fun getUserInfo(id: String)
    : NetworkState<BaseItemModel> =
        callRemoteApi { githubApiService.getUser(id) }

    suspend fun getUserRepos(id: String)
    : NetworkState<List<BaseItemModel>> =
        callRemoteApi { githubApiService.getUserRepos(id) }

    suspend fun getUserEvents(id: String, page: Int)
    : NetworkState<List<BaseItemModel>> =
        callRemoteApi { githubApiService.getUserEvent(id, page) }

}