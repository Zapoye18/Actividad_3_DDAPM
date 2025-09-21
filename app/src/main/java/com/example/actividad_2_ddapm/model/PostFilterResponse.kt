package com.example.actividad_2_ddapm.model
import com.google.gson.annotations.SerializedName

data class PostFilterResponse(
    @SerializedName("d") val d: PostFilterContainer
)

data class PostFilterContainer(
    @SerializedName("ExecuteResult") val executeResult: String,
    @SerializedName("Message") val message: String,
    @SerializedName("Posts") val posts: List<Posts>
)

data class Posts(
    @SerializedName("PostID") val postId: Int,
    @SerializedName("UserId") val userId: Int,
    @SerializedName("CompleteName") val completeName: String,
    @SerializedName("CampusID") val campusId: Int,
    @SerializedName("CampusName") val campusName: String,
    @SerializedName("Message") val message: String,
    @SerializedName("TimeStamp") val timeStamp: String,
)
