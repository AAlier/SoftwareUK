package com.softwareuk.data

import com.google.gson.annotations.SerializedName

data class ContentResponse<T>(
    @SerializedName("content")
    val content: T,
    @SerializedName("status")
    val status: String
)

enum class Status {
    @SerializedName("ok")
    OK,

    @SerializedName("error")
    ERROR
}