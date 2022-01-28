package com.project.githubexample.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


sealed class BaseItemModel() {

    //user info
    @Parcelize
    data class UserInfo(
        val login: String,
        val id: Long,

        @Json(name = "avatar_url")
        val avatarURL: String,

        val url: String,

        @Json(name = "html_url")
        val htmlURL: String,

        @Json(name = "repos_url")
        val reposURL: String,

        @Json(name = "events_url")
        val eventsURL: String,

        val type: String,

        val name: String?,
        val company: String?,
        val blog: String?,
        val location: String?,
        val email: String? = null,
        val bio: String?,

        @Json(name = "created_at")
        val createdAt: String,

        @Json(name = "updated_at")
        val updatedAt: String
    ) : BaseItemModel(), Parcelable


    //repository info
    @Parcelize
    data class RepoInfo(
        val id: Long,
        val name: String,

        @Json(name = "full_name")
        val fullName: String,

        val private: Boolean,
        val owner: Owner,

        @Json(name = "html_url")
        val htmlURL: String,

        val description: String?,
        val fork: Boolean,
        val url: String,

        @Json(name = "stargazers_count")
        val stargazersCount: Int,

        val language: String?
    ) : BaseItemModel(), Parcelable


    //event info
    @Parcelize
    data class EventInfo(
        val id: String,
        val type: String,
        val actor: Actor,
        val repo: Repo,
        val payload: Payload,
        val public: Boolean,

        @Json(name = "created_at")
        val createdAt: String
    ) : BaseItemModel(), Parcelable
}
