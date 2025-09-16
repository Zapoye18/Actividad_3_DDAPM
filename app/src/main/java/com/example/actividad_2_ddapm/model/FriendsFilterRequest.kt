package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName

data class FriendsFilterRequest(
    @SerializedName("FriendsFilter") val friendsFilter: FriendsFilterData
)


data class FriendsFilterData(
    @SerializedName("LoggedUserID") val loggedUserId: Int,
    @SerializedName("CampusID") val campusId: Int,
    @SerializedName("Name") val name: String

)