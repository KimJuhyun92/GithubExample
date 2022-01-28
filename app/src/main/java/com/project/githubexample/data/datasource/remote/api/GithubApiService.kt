package com.project.githubexample.data.datasource.remote.api

import com.project.githubexample.data.dto.BaseItemModel
import com.project.githubexample.data.dto.SearchUserModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("/search/users")
    suspend fun searchUsers(@Query("q") id: String, @Query("page") page: Int) : SearchUserModel

    @GET("/users/{username}")
    suspend fun getUser(@Path("username") name: String) : BaseItemModel.UserInfo

    @GET("/users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") name: String, @Query("per_page") perPage: Int = 3) : List<BaseItemModel.RepoInfo>

    @GET("/users/{username}/events")
    suspend fun getUserEvent(
        @Path("username") name: String, @Query("page") page: Int) : List<BaseItemModel.EventInfo>


}