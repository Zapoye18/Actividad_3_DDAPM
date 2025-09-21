package com.example.actividad_2_ddapm.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("d") val d: PostContainer
)

data class PostContainer(
    @SerializedName("ExecuteResult") val executeResult: String,
    @SerializedName("Message") val message: String,
)