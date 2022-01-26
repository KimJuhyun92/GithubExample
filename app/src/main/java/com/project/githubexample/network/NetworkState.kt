package com.project.githubexample.network

import androidx.annotation.Keep

@Keep
sealed class NetworkState<out T> {
    @Keep
    data class Success<out T>(val result: T) : NetworkState<T>()

    @Keep
    data class Failure(val throwable: Throwable?) : NetworkState<Nothing>()
}