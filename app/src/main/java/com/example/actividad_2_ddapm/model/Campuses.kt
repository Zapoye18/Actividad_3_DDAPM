package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName
data class CampusesResponse(
    @SerializedName("Campuses") val campuses: List<Campuses>,

)

data class Campuses(
    @SerializedName("CampusID") val campusID: Int,
    @SerializedName("CampusName") val campusName: String,
    @SerializedName("IsActive") val isActive: Boolean
)