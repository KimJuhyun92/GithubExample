package com.project.githubexample.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    val login: String,
    val id: Long,

    @Json(name = "node_id")
    val nodeID: String,

    @Json(name = "avatar_url")
    val avatarURL: String,

    val url: String,

    @Json(name = "html_url")
    val htmlURL: String,

    @Json(name = "organizations_url")
    val organizationsURL: String,

    @Json(name = "repos_url")
    val reposURL: String,

    @Json(name = "events_url")
    val eventsURL: String
): Parcelable
