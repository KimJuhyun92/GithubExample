package com.project.githubexample.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RetrofitModule {

}

suspend fun <T> callRemoteApi(api: suspend () -> T) =
    withContext(Dispatchers.IO) {
        try {
            NetworkState.Success(api())
        } catch (throwable: Throwable) {
            NetworkState.Failure(throwable)
        }
    }