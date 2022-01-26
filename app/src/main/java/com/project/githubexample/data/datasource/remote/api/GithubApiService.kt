package com.project.githubexample.data.datasource.remote.api

import com.project.githubexample.data.dto.User
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubApiService {

    @Headers("accept: application/vnd.github.v3+json")
    @GET("/search/users")
    suspend fun searchUsers(@Query("q") id: String, @Query("page") page: Int) : User
}