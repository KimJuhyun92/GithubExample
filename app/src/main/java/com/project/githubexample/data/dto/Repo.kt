package com.project.githubexample.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo (
    val id: Long,
    val name: String,
    val url: String
) : Parcelable