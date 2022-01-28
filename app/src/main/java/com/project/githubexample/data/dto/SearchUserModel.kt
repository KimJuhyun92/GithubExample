package com.project.githubexample.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchUserModel(
    val totalCount: Int?,
    val incompleteResults: Boolean?,
    val items: List<Items>
) : Parcelable
