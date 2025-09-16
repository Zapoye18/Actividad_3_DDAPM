package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName

data class FriendsFilterResponse(
    @SerializedName("d") val d: FriendsContainer
)

data class FriendsContainer(
    @SerializedName("ExecuteResult") val executeResult: String,
    @SerializedName("Message") val message: String,
    @SerializedName("Friends") val friends: List<Friend>
)

data class Friend(
    @SerializedName("UserID") val userId: Int,
    @SerializedName("CompleteName") val completeName: String,
    @SerializedName("Email") val email: String,
    @SerializedName("StudentNumber") val studentNumber: Int,
    @SerializedName("CampusID") val campusId: Int,
    @SerializedName("CampusName") val campus: String,
    @SerializedName("IsFriend") val isFriend: String
)
