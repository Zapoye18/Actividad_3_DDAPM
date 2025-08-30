package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("d") val d: UserLoggedContainer
)

data class UserLoggedContainer(
    @SerializedName("UserLogged") val users: List<UserLogged>
)

data class UserLogged(
    @SerializedName("UserId") val userId: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("LastName") val lastName: String,
    @SerializedName("Email") val email: String,
    @SerializedName("StudentNumber") val studentNumber: Int,
    @SerializedName("CampusID") val campusId: Int,
    @SerializedName("Campus") val campus: String
)

