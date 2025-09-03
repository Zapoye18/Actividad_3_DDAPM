package com.example.actividad_2_ddapm.model

import com.google.gson.annotations.SerializedName

data class NewUserRequest(
    @SerializedName("NewUser") val newUser: NewUserDatos
)
data class NewUserDatos(
    @SerializedName("Name") val name: String,
    @SerializedName("LastName") val lastName: String,
    @SerializedName("Email") val email: String,
    @SerializedName("Password") val password: String,
    @SerializedName("StudentNumber") val studentNumber: Int,
    @SerializedName("CampusID") val campusId: Int
    )
