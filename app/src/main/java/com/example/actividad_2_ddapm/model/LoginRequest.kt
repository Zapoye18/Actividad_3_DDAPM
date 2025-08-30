package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("datos") val datos: LoginDatos
)

data class LoginDatos(
    @SerializedName("Email") val email: String,
    @SerializedName("Password") val password: String,
)


