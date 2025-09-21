package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName

data class PostFilterRequest(
    @SerializedName("PostFilter") val postFilter: PostFilterData
)

data class PostFilterData(
    @SerializedName("LoggedUserID") val loggedUserID: Int
)
