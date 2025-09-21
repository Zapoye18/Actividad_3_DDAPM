package com.example.actividad_2_ddapm.model

import com.google.gson.annotations.SerializedName

data class PostRequest(
    @SerializedName("Post") val post: PostData
)

data class PostData(
    @SerializedName("LoggedUserID") val loggedUserID: Int,
    @SerializedName("Message") val message: String
)