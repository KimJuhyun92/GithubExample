package com.project.githubexample.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val totalCount : Int?,
    val incompleteResults : Boolean?,
    val items : List<Items>
) : Parcelable

@Parcelize
data class Items(
    val login : String?,
    val id : Int?,
    val avatarUrl : String?,
    val url : String?,
    val htmlUrl : String?,
    val followersUrl : String?,
    val followingUrl : String?,
    val subscriptionsUrl : String?,
    val organizationsUrl : String?,
    val reposUrl : String?,
    val eventsUrl : String?,
    val receivedEventsUrl : String?
) : Parcelable
