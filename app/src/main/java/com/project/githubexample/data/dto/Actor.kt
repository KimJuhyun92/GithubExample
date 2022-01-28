package com.project.githubexample.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor (
    val id: Int,
    val login: String,

    @Json(name = "display_login")
    val displayLogin: String? = null,

    val url: String,

    @Json(name = "avatar_url")
    val avatarURL: String
) : Parcelable
