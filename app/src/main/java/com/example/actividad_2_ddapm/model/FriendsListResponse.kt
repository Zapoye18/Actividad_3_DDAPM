package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName

data class FriendsListResponse(
    @SerializedName("d") val d: FriendsListResponseContainer
)

data class FriendsListResponseContainer(
    @SerializedName("ExecuteResult") val executeResult: String,
    @SerializedName("Message") val message: String,
)