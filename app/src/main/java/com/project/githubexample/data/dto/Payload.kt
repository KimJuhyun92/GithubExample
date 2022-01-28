package com.project.githubexample.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payload (
    val action: String? = null
) : Parcelable