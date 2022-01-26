package com.project.githubexample.data.datasource.remote

import com.project.githubexample.data.datasource.remote.api.GithubApiService
import com.project.githubexample.data.dto.User
import com.project.githubexample.network.NetworkState
import com.project.githubexample.network.callRemoteApi

class GithubRemoteSource ( private val githubApiService: GithubApiService) {

    suspend fun searchUsers(id: String, page: Int)
    : NetworkState<User> =
        callRemoteApi { githubApiService.searchUsers(id, page) }
}