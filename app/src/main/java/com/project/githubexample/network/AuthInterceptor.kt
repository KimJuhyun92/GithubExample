package com.project.githubexample.network

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = "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"

        val builder: Request.Builder
        var url = chain.request().url.toString()

        Logger.i("url = $url")

        builder = chain.request().newBuilder()
            .url(url)
            .header("Authorization", "token $accessToken")
        return chain.proceed(builder.build())
    }
}