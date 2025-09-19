package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName

data class FriendsListRequest(
    @SerializedName("FriendsList") val friendsFist: FriendsListDatos
)

data class FriendsListDatos(
    @SerializedName("LoggedUserID") val loggedUserID: Int,
    @SerializedName("Friends") val friends: String
)

