package com.project.githubexample.network

import com.project.githubexample.BuildConfig.GithubApiKey
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = GithubApiKey

        val builder: Request.Builder
        var url = chain.request().url.toString()

        builder = chain.request().newBuilder()
            .url(url)
            .header("Content", "application/vnd.github.v3+json")
            .header("Authorization", "token $accessToken")
        return chain.proceed(builder.build())
    }
}