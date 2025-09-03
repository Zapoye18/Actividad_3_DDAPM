package com.example.actividad_2_ddapm.model

import com.google.gson.annotations.SerializedName

data class NewUserResponse(
    @SerializedName("d") val d: UserLoggedContainer2
)

data class UserLoggedContainer2(
    @SerializedName("ExecuteResult") val executeResult: String,
    @SerializedName("Message") val message: String,
)

